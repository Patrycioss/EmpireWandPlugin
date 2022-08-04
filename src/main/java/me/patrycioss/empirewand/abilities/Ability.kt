package me.patrycioss.empirewand.abilities

import org.bukkit.event.player.PlayerInteractEvent

interface Ability
{
    fun activate(playerInteractEvent: PlayerInteractEvent)
}