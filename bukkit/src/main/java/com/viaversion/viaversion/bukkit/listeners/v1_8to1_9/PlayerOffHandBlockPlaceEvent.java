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

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event fired when a 1.9+ client attempts to place a block using the off-hand
 * while connected to a 1.8 server through ViaVersion.
 * <p>
 * Since 1.8 servers don't support off-hand, ViaVersion cancels this packet by default.
 * This event allows plugins to detect the placement attempt and implement custom behavior.
 * <p>
 * This event is fired BEFORE ViaVersion cancels the packet.
 */
public class PlayerOffHandBlockPlaceEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final int blockX;
    private final int blockY;
    private final int blockZ;
    private final BlockFace face;
    private boolean cancelled;

    /**
     * Creates a new off-hand block place event.
     *
     * @param player the player who attempted to place a block
     * @param blockX the X coordinate of the clicked block
     * @param blockY the Y coordinate of the clicked block
     * @param blockZ the Z coordinate of the clicked block
     * @param face   the face of the block that was clicked
     */
    public PlayerOffHandBlockPlaceEvent(@NotNull Player player, int blockX, int blockY, int blockZ, @NotNull BlockFace face) {
        super(true); // async = true since this is called from netty thread
        this.player = player;
        this.blockX = blockX;
        this.blockY = blockY;
        this.blockZ = blockZ;
        this.face = face;
    }

    /**
     * Gets the player who attempted to place a block with their off-hand.
     *
     * @return the player
     */
    @NotNull
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the X coordinate of the clicked block.
     *
     * @return the X coordinate
     */
    public int getBlockX() {
        return blockX;
    }

    /**
     * Gets the Y coordinate of the clicked block.
     *
     * @return the Y coordinate
     */
    public int getBlockY() {
        return blockY;
    }

    /**
     * Gets the Z coordinate of the clicked block.
     *
     * @return the Z coordinate
     */
    public int getBlockZ() {
        return blockZ;
    }

    /**
     * Gets the face of the block that was clicked.
     *
     * @return the block face
     */
    @NotNull
    public BlockFace getFace() {
        return face;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets whether this event is cancelled.
     * Note: The underlying block placement is always cancelled by ViaVersion since 1.8 doesn't support off-hand.
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
