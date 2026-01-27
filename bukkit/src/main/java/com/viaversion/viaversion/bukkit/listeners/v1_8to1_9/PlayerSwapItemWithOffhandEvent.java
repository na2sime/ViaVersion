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
import org.jetbrains.annotations.NotNull;

/**
 * Event fired when a 1.9+ client attempts to swap items between main hand and offhand
 * (by pressing the F key) while connected to a 1.8 server through ViaVersion.
 * <p>
 * Since 1.8 servers don't support offhand, ViaVersion cancels this packet by default.
 * This event allows plugins to detect the swap attempt and implement custom behavior.
 */
public class PlayerSwapItemWithOffhandEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private boolean cancelled;

    /**
     * Creates a new swap item event.
     *
     * @param player the player who attempted to swap items
     */
    public PlayerSwapItemWithOffhandEvent(@NotNull Player player) {
        super(true); // async = true since this is called from netty thread
        this.player = player;
    }

    /**
     * Gets the player who attempted to swap items.
     *
     * @return the player
     */
    @NotNull
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets whether this event is cancelled.
     * Note: The underlying swap action is always cancelled by ViaVersion since 1.8 doesn't support offhand.
     * This cancellation only affects whether your plugin's logic should proceed.
     *
     * @param cancelled true to cancel
     */
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
