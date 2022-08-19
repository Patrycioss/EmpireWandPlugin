package me.patrycioss.empirewand.abilities

import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerInteractEvent

class FireBall : Ability
{
    private val server = Bukkit.getServer()


    override fun activate(playerInteractEvent: PlayerInteractEvent)
    {
        server.logger.info("[EmpireWand] Using fireball")




    }
}