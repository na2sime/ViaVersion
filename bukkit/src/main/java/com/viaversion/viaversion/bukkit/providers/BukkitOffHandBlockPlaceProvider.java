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
import com.viaversion.viaversion.bukkit.listeners.v1_8to1_9.PlayerOffHandBlockPlaceEvent;
import com.viaversion.viaversion.protocols.v1_8to1_9.provider.OffHandBlockPlaceProvider;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

/**
 * Bukkit implementation of {@link OffHandBlockPlaceProvider} that fires a
 * {@link PlayerOffHandBlockPlaceEvent} when a 1.9+ client attempts to place
 * a block with the off-hand on a 1.8 server.
 */
public class BukkitOffHandBlockPlaceProvider extends OffHandBlockPlaceProvider {

    private static final BlockFace[] FACES = {
        BlockFace.DOWN,   // 0
        BlockFace.UP,     // 1
        BlockFace.NORTH,  // 2
        BlockFace.SOUTH,  // 3
        BlockFace.WEST,   // 4
        BlockFace.EAST    // 5
    };

    @Override
    public boolean onOffHandBlockPlace(final UserConnection connection, final int blockX, final int blockY, final int blockZ, final int face) {
        final UUID playerUuid = connection.getProtocolInfo().getUuid();
        if (playerUuid == null) {
            return true;
        }

        final Player player = Bukkit.getPlayer(playerUuid);
        if (player == null) {
            return true;
        }

        final BlockFace blockFace = face >= 0 && face < FACES.length ? FACES[face] : BlockFace.SELF;

        // Fire the event asynchronously since we're on the netty thread
        final PlayerOffHandBlockPlaceEvent event = new PlayerOffHandBlockPlaceEvent(player, blockX, blockY, blockZ, blockFace);
        Bukkit.getPluginManager().callEvent(event);

        // Always return true to cancel the packet (1.8 doesn't support off-hand)
        return true;
    }
}
