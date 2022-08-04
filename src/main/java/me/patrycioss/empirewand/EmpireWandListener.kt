package me.patrycioss.empirewand

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.util.logging.Logger


class EmpireWandListener(empireWand: EmpireWand) : Listener
{
    private val logger : Logger = Bukkit.getLogger()
    private val server : Server = Bukkit.getServer()
    private val world : World? = server.getWorld("world")

    init
    {
        if (world == null) throw Exception("Couldn't find world :/")

        empireWand.server.pluginManager.registerEvents(this, empireWand)

        logger.info("I have been constructed!")
    }


    @EventHandler
    fun onLogin(playerJoinEvent: PlayerJoinEvent)
    {
        server.broadcast(Component.text(playerJoinEvent.player.name + " has joined the game!"))
    }

    @EventHandler
    fun onUseWand(playerInteractEvent: PlayerInteractEvent)
    {
        logger.info(playerInteractEvent.eventName)

        if (playerInteractEvent.hasItem())
        {
            if (playerInteractEvent.item!!.type == Material.BLAZE_ROD)
            {
                val player : Player = playerInteractEvent.player

                //Makes sure there is always an explosion even if the closest block is an air block
                when (val closestBlock = player.getTargetBlock(EmpireWand.range))
                {
                    null ->
                    {
                        world!!.createExplosion(
                            //Current position + direction * range
                            player.location.add(player.location.direction.multiply(EmpireWand.range))
                            , 5f, false)
                    }

                    else -> EmpireWand.world!!.createExplosion(closestBlock.location, 5f, false)
                }

                player.sendMessage("BOOOOOM!")
            }
        }
    }
}