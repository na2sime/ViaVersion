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
package com.viaversion.viaversion.protocols.v1_8to1_9.provider;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.platform.providers.Provider;

/**
 * Provider for handling off-hand block placement attempts from 1.9+ clients on 1.8 servers.
 * Platform implementations can override this to fire custom events.
 */
public class OffHandBlockPlaceProvider implements Provider {

    /**
     * Called when a 1.9+ client attempts to place a block with the off-hand.
     *
     * @param connection the user connection
     * @param blockX     the X coordinate of the clicked block
     * @param blockY     the Y coordinate of the clicked block
     * @param blockZ     the Z coordinate of the clicked block
     * @param face       the block face clicked (0=DOWN, 1=UP, 2=NORTH, 3=SOUTH, 4=WEST, 5=EAST)
     * @return true if the packet should be cancelled (default), false to let it through
     */
    public boolean onOffHandBlockPlace(final UserConnection connection, final int blockX, final int blockY, final int blockZ, final int face) {
        // Default implementation cancels the packet - platform implementations override this
        return true;
    }
}
