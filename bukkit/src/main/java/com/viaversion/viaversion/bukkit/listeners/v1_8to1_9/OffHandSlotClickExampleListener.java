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
 * Example listener demonstrating how to handle the {@link PlayerOffHandSlotClickEvent}.
 * <p>
 * To use this in your plugin:
 * <pre>{@code
 * public class MyPlugin extends JavaPlugin {
 *     @Override
 *     public void onEnable() {
 *         getServer().getPluginManager().registerEvents(new OffHandSlotClickListener(), this);
 *     }
 * }
 *
 * public class OffHandSlotClickListener implements Listener {
 *     @EventHandler
 *     public void onOffHandSlotClick(PlayerOffHandSlotClickEvent event) {
 *         Player player = event.getPlayer();
 *         // Your custom logic here
 *         player.sendMessage("You clicked on the off-hand slot!");
 *     }
 * }
 * }</pre>
 */
public class OffHandSlotClickExampleListener implements Listener {

    @EventHandler
    public void onOffHandSlotClick(PlayerOffHandSlotClickEvent event) {
        Player player = event.getPlayer();

        // Example: Send a message to the player
        player.sendMessage("§cThe off-hand slot is not supported on this server version!");

        // Example: Log the attempt with click type
        String clickInfo = "click type: " + event.getClickType();
        if (event.getClickType() == PlayerOffHandSlotClickEvent.ClickType.NUMBER_KEY) {
            clickInfo += ", hotbar button: " + (event.getHotbarButton() + 1);
        }
        System.out.println("[ViaVersion] Player " + player.getName() + " clicked on off-hand slot (" + clickInfo + ")");

        // Example: Handle different click types
        switch (event.getClickType()) {
            case LEFT:
                // Handle left click
                break;
            case RIGHT:
                // Handle right click
                break;
            case SHIFT_LEFT:
            case SHIFT_RIGHT:
                // Handle shift-click
                break;
            case NUMBER_KEY:
                // Handle hotbar key press (1-9)
                int hotbarSlot = event.getHotbarButton();
                break;
        }

        // Example: Trigger custom behavior
        // You could implement your own off-hand-like system here:
        // - Store items in a custom "off-hand" storage
        // - Open a custom equipment menu
        // - Swap with a virtual off-hand item
        // etc.
    }
}
