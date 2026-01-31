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
import com.viaversion.viaversion.api.minecraft.item.Item;
import com.viaversion.viaversion.bukkit.listeners.v1_8to1_9.PlayerOffHandSlotDragEvent;
import com.viaversion.viaversion.protocols.v1_8to1_9.provider.OffHandSlotDragProvider;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BukkitOffHandSlotDragProvider extends OffHandSlotDragProvider {

    @Override
    public boolean onOffHandSlotDrag(final UserConnection connection, final DragType dragType, final Item cursorItem) {
        final UUID playerUuid = connection.getProtocolInfo().getUuid();
        if (playerUuid == null) {
            return false;
        }

        final Player player = Bukkit.getPlayer(playerUuid);
        if (player == null) {
            return false;
        }

        final PlayerOffHandSlotDragEvent.DragType bukkitDragType = switch (dragType) {
            case LEFT -> PlayerOffHandSlotDragEvent.DragType.LEFT;
            case RIGHT -> PlayerOffHandSlotDragEvent.DragType.RIGHT;
            case MIDDLE -> PlayerOffHandSlotDragEvent.DragType.MIDDLE;
        };

        final ItemStack bukkitItem = cursorItem != null ? convertItem(cursorItem) : null;

        final PlayerOffHandSlotDragEvent event = new PlayerOffHandSlotDragEvent(player, bukkitDragType, bukkitItem);
        Bukkit.getPluginManager().callEvent(event);
        return event.isCancelled();
    }

    private static ItemStack convertItem(final Item item) {
        if (item == null || item.identifier() == 0) {
            return null;
        }
        final ItemStack stack = new ItemStack(Material.values()[item.identifier()], item.amount());
        if (item.data() != 0) {
            stack.setDurability(item.data());
        }
        return stack;
    }
}
