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
import com.viaversion.viaversion.api.minecraft.item.Item;
import com.viaversion.viaversion.api.platform.providers.Provider;

/**
 * Provider for handling off-hand slot (slot 45) drag attempts from 1.9+ clients on 1.8 servers.
 * Platform implementations can override this to fire custom events.
 */
public class OffHandSlotDragProvider implements Provider {

    public enum DragType {
        LEFT,
        RIGHT,
        MIDDLE
    }

    /**
     * Called when a 1.9+ client includes the off-hand slot (slot 45) in a drag operation.
     *
     * @param connection the user connection
     * @param dragType   the type of drag
     * @param cursorItem the item being dragged (cursor item)
     * @return true if the drag on slot 45 should be cancelled
     */
    public boolean onOffHandSlotDrag(final UserConnection connection, final DragType dragType, final Item cursorItem) {
        return false;
    }
}
