package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


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
    private KillManager killManager;
    private ScoreboardManager scoreboardManager;
    private TNTManager tntManager;
    private UpgradeManager upgradeManager;
    private EliminationManager eliminationManager;
    private RespawnManager respawnManager;
    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();
        swordManager =
                new SwordManager();
        playerManager = new PlayerManager();
        arenaManager = new ArenaManager();
        gameEndManager =
                new GameEndManager();
        
        eliminationManager = new EliminationManager();
        armorManager =
                new ArmorManager();
        respawnManager =
                new RespawnManager();
        npcManager = new NPCManager();
        tntManager =
                new TNTManager();
        toolManager =
                new ToolManager();
        arenaReset =
                new ArenaReset();
        killManager = new KillManager();
        upgradeManager =
                new UpgradeManager();
        scoreboardManager =
                new ScoreboardManager();
        
        new ScoreboardTask()
        .runTaskTimer(
                this,
                20L,
                20L
        );
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
                new TeamDamageListener(),
                this
        );
        Bukkit.getPluginManager()
        .registerEvents(
                new ArmorDropListener(),
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

        getLogger().info("BedWars desligado!");
    }

    public static Bedwars getInstance() {
        return instance;
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