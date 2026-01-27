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
 * Provider for handling off-hand slot (slot 45) click attempts from 1.9+ clients on 1.8 servers.
 * Platform implementations can override this to fire custom events.
 */
public class OffHandSlotClickProvider implements Provider {

    /**
     * Click type for off-hand slot interactions.
     */
    public enum ClickType {
        LEFT,
        RIGHT,
        SHIFT_LEFT,
        SHIFT_RIGHT,
        NUMBER_KEY
    }

    /**
     * Called when a 1.9+ client clicks on the off-hand slot (slot 45).
     *
     * @param connection   the user connection
     * @param clickType    the type of click performed
     * @param hotbarButton the hotbar button (0-8) if clickType is NUMBER_KEY, otherwise -1
     */
    public void onOffHandSlotClick(final UserConnection connection, final ClickType clickType, final int hotbarButton) {
        // Default implementation does nothing - platform implementations override this
    }
}
