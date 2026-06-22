package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;


public class Bedwars extends JavaPlugin {

    private static Bedwars instance;
    private ShopManager shopManager;
    private ArenaManager arenaManager;
    private PlayerManager playerManager;
    private NPCManager npcManager;
    private SpectatorManager spectatorManager;
    private GeneratorManager generatorManager;
    private SwordManager swordManager;
    private ArenaReset arenaReset;
    private ArmorManager armorManager;
    private ToolManager toolManager;
    private GameEndManager gameEndManager;
    private CosmeticGeneratorManager cosmeticGeneratorManager;
    private KillManager killManager;
    private ScoreboardManager scoreboardManager;
    private TNTManager tntManager;
    private LobbyScoreboard lobbyScoreboard;
    private UpgradeManager upgradeManager;
    private RewardManager rewardManager;
    private EliminationManager eliminationManager;
    private RespawnManager respawnManager;
    private MySQL mysql;
    private CosmeticGeneratorFile cosmeticGeneratorFile;
    private LevelManager levelManager;
    private Location lobbySpawn;
    private LobbyFile lobbyFile;
    private StatsManager statsManager;
    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();
        cosmeticGeneratorFile =
                new CosmeticGeneratorFile();
        swordManager =
                new SwordManager();
        playerManager = new PlayerManager();

        npcManager = new NPCManager();
        npcManager.cleanupArenaNPCs();
        Bukkit.getPluginManager()
        .registerEvents(
                new LobbyJumpPadListener(),
                this
        );
        arenaManager = new ArenaManager();
        gameEndManager =
                new GameEndManager();
        mysql =
                new MySQL();
        rewardManager =
                new RewardManager();
        levelManager =
                new LevelManager();
        mysql.connect();
        generatorManager =
                new GeneratorManager();
        mysql.createTables();
        cosmeticGeneratorManager =
                new CosmeticGeneratorManager();
        cosmeticGeneratorManager.load();
        statsManager =
                new StatsManager(mysql);
        eliminationManager = new EliminationManager();
        armorManager =
                new ArmorManager();
        respawnManager =
                new RespawnManager();
        if(getServer()
                .getPluginManager()
                .getPlugin(
                        "PlaceholderAPI"
                ) != null) {

            new BedwarsExpansion()
                    .register();
        }
    
        tntManager =
                new TNTManager();
        toolManager =
                new ToolManager();
        arenaReset =
                new ArenaReset();
        killManager = new KillManager();

        lobbyFile = new LobbyFile();
        upgradeManager =
                new UpgradeManager();
        scoreboardManager =
                new ScoreboardManager();
        lobbyScoreboard =
                new LobbyScoreboard();
        new ScoreboardTask()
        .runTaskTimer(
                this,
                20L,
                20L
        );
        if (getLobbySpawn() == null) {
        	setLobbySpawn(new Location(Bukkit.getWorld("world"), 0 , 64 ,0));
        }
        lobbySpawn = new Location(Bukkit.getWorld(getConfig().getString("Lobby.World")), getConfig().getInt("Lobby.X"), getConfig().getInt("Lobby.Y"), getConfig().getInt("Lobby.Z"));
        spectatorManager = new SpectatorManager();
        shopManager = new ShopManager();
        getCommand("bw")
        .setExecutor(new BWCommand());
        Bukkit.getPluginManager()
        .registerEvents(
                new BedWarsListeners(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new SpectatorListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new BlockTracker(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new TNTPlaceListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new VoidListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new TNTExplodeListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new LobbyItemListener(),
                this
        );
        npcManager.loadNPCs();
       
        Bukkit.getPluginManager()
        .registerEvents(
                new TeamDamageListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new LobbyJoinListener(),
                this
        );
        new LobbyScoreboardTask()
        .runTaskTimer(
                this,
                20L,
                40L
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new ArenaSelectorListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new LobbyProtectionListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new MenuListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new LobbyDropListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new ArmorDropListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new ArenaBlockBreakListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new DamageListener(),
                this
        );
        
        Bukkit.getPluginManager()
        .registerEvents(
                new UpgradeListener(),
                this
        );
        new LobbyItemCleanupTask()
        .runTaskTimer(
                this,
                200L,
                200L
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new NPCListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new ShopListener(),
                this
        );
        getLogger().info("BedWars carregado!");
    }

    @Override
    public void onDisable() {
    	for(Player player :
            Bukkit.getOnlinePlayers()) {

        PlayerStats stats =
                getStatsManager()
                        .getStats(
                                player.getUniqueId()
                        );

        getStatsManager()
                .save(stats);
        statsManager.saveAll();
    }
    	for(ArmorStand stand : Bukkit.getWorld(getConfig().getString("Lobby.World")).getEntitiesByClass(ArmorStand.class)) {
if (stand.hasMetadata("KPBEDWARS_COSMETIC"))
    	    if(stand != null
    	            && !stand.isDead()) {

    	        stand.remove();
    	    }
getLogger().info("Stands cosmeticos removidos");
    	}
        getLogger().info("BedWars desligado!");
    }
    public LobbyScoreboard getLobbyScoreboard() {
        return lobbyScoreboard;
    }
    public Location getLobbySpawn() {
        return lobbySpawn;
    }
    public LobbyFile getLobbyFile() {
        return lobbyFile;
    }
    public void setLobbySpawn(
            Location lobbySpawn) {

        this.lobbySpawn = lobbySpawn;
    }
    public MySQL getMysql() {
        return mysql;
    }
    public RewardManager getRewardManager() {
        return rewardManager;
    }
    public CosmeticGeneratorFile getCosmeticGeneratorFile() {
        return cosmeticGeneratorFile;
    }
    public StatsManager getStatsManager() {
        return statsManager;
    }
    public static Bedwars getInstance() {
        return instance;
    }
    public LevelManager getLevelManager() {
        return levelManager;
    }
    
    public ArmorManager getArmorManager() {
        return armorManager;
    }
    public SwordManager getSwordManager() {
        return swordManager;
    }
    public RespawnManager getRespawnManager() {
        return respawnManager;
    }
    public GameEndManager getGameEndManager() {
        return gameEndManager;
    }
    public ArenaReset getArenaReset() {
        return arenaReset;
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
public EliminationManager getEliminationManager() {
	return eliminationManager;
}
    public TNTManager getTntManager() {
        return tntManager;
    }
    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }
    public GeneratorManager getGeneratorManager() {
        return generatorManager;
    }
    public SpectatorManager getSpectatorManager() {
        return spectatorManager;
    }
    public NPCManager getNpcManager() {
        return npcManager;
    }
    public KillManager getKillManager() {
        return killManager;
    }
    public ToolManager getToolManager() {
        return toolManager;
    }
    public UpgradeManager getUpgradeManager() {
        return upgradeManager;
    }
    public ShopManager getShopManager() {
        return shopManager;
    }
    public ArenaManager getArenaManager() {
        return arenaManager;
    }
}