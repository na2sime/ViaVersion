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
import com.viaversion.viaversion.bukkit.listeners.v1_8to1_9.PlayerSwapItemWithOffhandEvent;
import com.viaversion.viaversion.protocols.v1_8to1_9.provider.SwapHandsProvider;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Bukkit implementation of {@link SwapHandsProvider} that fires a
 * {@link PlayerSwapItemWithOffhandEvent} when a 1.9+ client attempts
 * to swap items on a 1.8 server.
 */
public class BukkitSwapHandsProvider extends SwapHandsProvider {

    @Override
    public void onSwapHands(final UserConnection connection) {
        final UUID playerUuid = connection.getProtocolInfo().getUuid();
        if (playerUuid == null) {
            return;
        }

        final Player player = Bukkit.getPlayer(playerUuid);
        if (player == null) {
            return;
        }

        // Fire the event asynchronously since we're on the netty thread
        final PlayerSwapItemWithOffhandEvent event = new PlayerSwapItemWithOffhandEvent(player);
        Bukkit.getPluginManager().callEvent(event);
    }
}
