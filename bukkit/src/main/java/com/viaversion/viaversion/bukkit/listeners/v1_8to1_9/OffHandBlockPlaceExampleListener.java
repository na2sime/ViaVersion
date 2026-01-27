/*
 * This file is part of ViaVersion - https://github.com/ViaVersion/ViaVersion
 * Copyright (C) 2016-2026 ViaVersion and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.viaversion.viaversion.bukkit.listeners.v1_8to1_9;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Example listener demonstrating how to handle the {@link PlayerOffHandBlockPlaceEvent}.
 * <p>
 * To use this in your plugin:
 * <pre>{@code
 * public class MyPlugin extends JavaPlugin {
 *     @Override
 *     public void onEnable() {
 *         getServer().getPluginManager().registerEvents(new OffHandBlockPlaceListener(), this);
 *     }
 * }
 *
 * public class OffHandBlockPlaceListener implements Listener {
 *     @EventHandler
 *     public void onOffHandBlockPlace(PlayerOffHandBlockPlaceEvent event) {
 *         Player player = event.getPlayer();
 *         // Your custom logic here
 *         player.sendMessage("You tried to place a block with your off-hand!");
 *     }
 * }
 * }</pre>
 */
public class OffHandBlockPlaceExampleListener implements Listener {

    @EventHandler
    public void onOffHandBlockPlace(PlayerOffHandBlockPlaceEvent event) {
        Player player = event.getPlayer();

        // Example: Send a message to the player
        player.sendMessage("§cOff-hand block placement is not supported on this server version!");

        // Example: Log the attempt with coordinates
        System.out.println("[ViaVersion] Player " + player.getName() + " attempted to place a block with off-hand at "
            + event.getBlockX() + ", " + event.getBlockY() + ", " + event.getBlockZ()
            + " (face: " + event.getFace() + ")");

        // Example: Trigger custom behavior
        // You could implement your own off-hand-like system here:
        // - Use the main hand item instead
        // - Open a custom inventory
        // - Execute a command
        // etc.
    }
}
