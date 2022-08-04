package me.patrycioss.empirewand.abilities

import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerInteractEvent

class FireBall : Ability
{
    override fun activate(playerInteractEvent: PlayerInteractEvent)
    {
        Bukkit.getServer().logger.info("[EmpireWand] Using fireball")
    }
}