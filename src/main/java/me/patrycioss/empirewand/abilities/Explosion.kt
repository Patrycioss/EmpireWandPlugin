package me.patrycioss.empirewand.abilities

import me.patrycioss.empirewand.EmpireWand
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent

class Explosion : Ability
{
    override fun activate(playerInteractEvent : PlayerInteractEvent)
    {
        val player : Player = playerInteractEvent.player

        //Makes sure there is always an explosion even if the closest block is an air block
        when (val closestBlock = player.getTargetBlock(EmpireWand.range))
        {
            null ->
            {
                player.world.createExplosion(
                    //Current position + direction * range + (0,1,0) #To make explosions feel better
                    player.location.add(player.location.direction.multiply(EmpireWand.range)).add(0.0,1.0,0.0)
                    , EmpireWand.explosionPower, false)
            }

            else -> player.world.createExplosion(closestBlock.location.add(0.0,1.0,0.0), 5f, false)
        }
    }

}