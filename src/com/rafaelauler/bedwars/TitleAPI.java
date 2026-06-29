package com.rafaelauler.bedwars;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class TitleAPI {

    public static void send(
            Player player,
            String title,
            String subtitle,
            int fadeIn,
            int stay,
            int fadeOut) {

        PlayerConnection connection =
                ((CraftPlayer) player)
                        .getHandle()
                        .playerConnection;

        PacketPlayOutTitle times =
                new PacketPlayOutTitle(
                        fadeIn,
                        stay,
                        fadeOut
                );

        connection.sendPacket(times);

        PacketPlayOutTitle titlePacket =
                new PacketPlayOutTitle(
                        PacketPlayOutTitle.EnumTitleAction.TITLE,
                        IChatBaseComponent.ChatSerializer.a(
                                "{\"text\":\""
                                + title
                                + "\"}"
                        )
                );

        PacketPlayOutTitle subPacket =
                new PacketPlayOutTitle(
                        PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                        IChatBaseComponent.ChatSerializer.a(
                                "{\"text\":\""
                                + subtitle
                                + "\"}"
                        )
                );

        connection.sendPacket(titlePacket);
        connection.sendPacket(subPacket);
    }
    public static void sendActionBar(Player player, String message) {

        if (player == null || !player.isOnline())
            return;

        IChatBaseComponent component =
                new ChatComponentText(message);

        PacketPlayOutChat packet =
                new PacketPlayOutChat(
                        component,
                        (byte) 2
                );

        ((CraftPlayer) player)
                .getHandle()
                .playerConnection
                .sendPacket(packet);
    }
}