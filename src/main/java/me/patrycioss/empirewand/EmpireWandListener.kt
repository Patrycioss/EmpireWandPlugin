package me.patrycioss.empirewand

import me.patrycioss.empirewand.abilities.Explosion
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import java.util.logging.Logger


class EmpireWandListener(empireWand: EmpireWand) : Listener
{
    private val logger : Logger = Bukkit.getLogger()

    init
    {
        empireWand.server.pluginManager.registerEvents(this, empireWand)

        logger.info("The auditor of this wand has been initialized!")
    }

    @EventHandler
    fun onUseWand(playerInteractEvent: PlayerInteractEvent)
    {
        when (playerInteractEvent.action)
        {
            Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK ->
            {
                if (playerInteractEvent.hasItem())
                {
                    when (playerInteractEvent.item!!.type)
                    {
                        Material.BLAZE_ROD -> Explosion(playerInteractEvent).activate()

                        else -> {}
                    }

                }
            }

            else -> {}
        }
    }
}