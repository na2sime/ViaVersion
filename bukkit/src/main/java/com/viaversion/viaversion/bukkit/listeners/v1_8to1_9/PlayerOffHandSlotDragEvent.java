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
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Event fired when a 1.9+ client includes the off-hand slot (slot 45) in a drag operation
 * while connected to a 1.8 server through ViaVersion.
 * <p>
 * If cancelled, the off-hand slot will be excluded from the drag. Other slots in the drag
 * will proceed normally.
 * <p>
 * This event is fired from the netty thread (async).
 */
public class PlayerOffHandSlotDragEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final DragType dragType;
    private final ItemStack cursorItem;
    private boolean cancelled;

    public enum DragType {
        LEFT,
        RIGHT,
        MIDDLE
    }

    /**
     * Creates a new off-hand slot drag event.
     *
     * @param player     the player performing the drag
     * @param dragType   the type of drag (left, right, or middle)
     * @param cursorItem the item being dragged (cursor item), may be null
     */
    public PlayerOffHandSlotDragEvent(@NotNull Player player, @NotNull DragType dragType, @Nullable ItemStack cursorItem) {
        super(true); // async = true since this is called from netty thread
        this.player = player;
        this.dragType = dragType;
        this.cursorItem = cursorItem;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    @NotNull
    public DragType getDragType() {
        return dragType;
    }

    /**
     * Gets the item being dragged (the cursor item).
     *
     * @return the cursor item, or null if empty
     */
    @Nullable
    public ItemStack getCursorItem() {
        return cursorItem;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
