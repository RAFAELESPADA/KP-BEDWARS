package com.rafaelauler.bedwars;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamGeneratorTask extends BukkitRunnable {

    private final TeamGenerator generator;

    private long ironTicks;
    private long goldTicks;

    public TeamGeneratorTask(
            TeamGenerator generator) {

        this.generator = generator;
    }

    @Override
    public void run() {

        BWTeam team =
                generator.getTeam();

        ironTicks++;
        goldTicks++;

        if (ironTicks >= team.getIronDelay()) {

            ironTicks = 0;

            spawn(Material.IRON_INGOT, 48);
        }

        if (goldTicks >= team.getGoldDelay()) {

            goldTicks = 0;

            spawn(Material.GOLD_INGOT, 8);
        }
    }

    private void spawn(
            Material material,
            int limit) {

        int amount = 0;

        for (org.bukkit.entity.Entity entity :
                generator.getLocation()
                        .getWorld()
                        .getNearbyEntities(
                                generator.getLocation(),
                                2,
                                2,
                                2)) {

            if (!(entity instanceof Item))
                continue;

            Item item = (Item) entity;

            if (item.getItemStack().getType()
                    != material)
                continue;

            amount += item.getItemStack().getAmount();
        }

        if (amount >= limit)
            return;

        generator.getLocation()
                .getWorld()
                .dropItemNaturally(
                        generator.getLocation(),
                        new ItemStack(material)
                );
    }
}