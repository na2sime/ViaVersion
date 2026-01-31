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
package com.viaversion.viaversion.bukkit.providers;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.bukkit.listeners.v1_8to1_9.PlayerOffHandSlotClickEvent;
import com.viaversion.viaversion.protocols.v1_8to1_9.provider.OffHandSlotClickProvider;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Bukkit implementation of {@link OffHandSlotClickProvider} that fires a
 * {@link PlayerOffHandSlotClickEvent} when a 1.9+ client clicks on the
 * off-hand slot (slot 45) on a 1.8 server.
 */
public class BukkitOffHandSlotClickProvider extends OffHandSlotClickProvider {

    @Override
    public boolean onOffHandSlotClick(final UserConnection connection, final ClickType clickType, final int hotbarButton) {
        final UUID playerUuid = connection.getProtocolInfo().getUuid();
        if (playerUuid == null) {
            return false;
        }

        final Player player = Bukkit.getPlayer(playerUuid);
        if (player == null) {
            return false;
        }

        // Convert ClickType
        final PlayerOffHandSlotClickEvent.ClickType bukkitClickType = switch (clickType) {
            case LEFT -> PlayerOffHandSlotClickEvent.ClickType.LEFT;
            case RIGHT -> PlayerOffHandSlotClickEvent.ClickType.RIGHT;
            case SHIFT_LEFT -> PlayerOffHandSlotClickEvent.ClickType.SHIFT_LEFT;
            case SHIFT_RIGHT -> PlayerOffHandSlotClickEvent.ClickType.SHIFT_RIGHT;
            case NUMBER_KEY -> PlayerOffHandSlotClickEvent.ClickType.NUMBER_KEY;
        };

        // Fire the event asynchronously since we're on the netty thread
        final PlayerOffHandSlotClickEvent event = new PlayerOffHandSlotClickEvent(player, bukkitClickType, hotbarButton);
        Bukkit.getPluginManager().callEvent(event);
        return event.isCancelled();
    }
}
