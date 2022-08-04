package me.patrycioss.empirewand.abilities

import me.patrycioss.empirewand.EmpireWand
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent

class Explosion constructor(private val playerInteractEvent: PlayerInteractEvent) : Ability
{
    override fun activate()
    {
        val player : Player = playerInteractEvent.player

        //Makes sure there is always an explosion even if the closest block is an air block
        when (val closestBlock = player.getTargetBlock(EmpireWand.range))
        {
            null ->
            {
                player.world.createExplosion(
                    //Current position + direction * range
                    player.location.add(player.location.direction.multiply(EmpireWand.range))
                    , 5f, false)
            }

            else -> player.world.createExplosion(closestBlock.location, 5f, false)
        }

        player.sendMessage("BOOOOOM!")
    }

}