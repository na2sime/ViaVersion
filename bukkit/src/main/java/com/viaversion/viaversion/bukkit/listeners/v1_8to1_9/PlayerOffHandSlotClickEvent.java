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
 * Event fired when a 1.9+ client clicks on the off-hand slot (slot 45) in their inventory
 * while connected to a 1.8 server through ViaVersion.
 * <p>
 * Since 1.8 servers don't support the off-hand slot, ViaVersion handles this specially.
 * This event allows plugins to detect the click and implement custom behavior.
 * <p>
 * This event is fired BEFORE ViaVersion processes the click.
 */
public class PlayerOffHandSlotClickEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final ClickType clickType;
    private final int hotbarButton;
    private boolean cancelled;

    /**
     * Type of click performed on the off-hand slot.
     */
    public enum ClickType {
        /** Normal left click */
        LEFT,
        /** Normal right click */
        RIGHT,
        /** Shift + left click */
        SHIFT_LEFT,
        /** Shift + right click */
        SHIFT_RIGHT,
        /** Number key press (1-9) */
        NUMBER_KEY
    }

    /**
     * Creates a new off-hand slot click event.
     *
     * @param player       the player who clicked
     * @param clickType    the type of click
     * @param hotbarButton the hotbar button (0-8) if NUMBER_KEY, -1 otherwise
     */
    public PlayerOffHandSlotClickEvent(@NotNull Player player, @NotNull ClickType clickType, int hotbarButton) {
        super(true); // async = true since this is called from netty thread
        this.player = player;
        this.clickType = clickType;
        this.hotbarButton = hotbarButton;
    }

    /**
     * Gets the player who clicked on the off-hand slot.
     *
     * @return the player
     */
    @NotNull
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the type of click performed.
     *
     * @return the click type
     */
    @NotNull
    public ClickType getClickType() {
        return clickType;
    }

    /**
     * Gets the hotbar button pressed if the click type is {@link ClickType#NUMBER_KEY}.
     *
     * @return the hotbar button (0-8), or -1 if not applicable
     */
    public int getHotbarButton() {
        return hotbarButton;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets whether this event is cancelled.
     * Note: The underlying slot interaction is handled specially by ViaVersion since 1.8 doesn't support off-hand.
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
