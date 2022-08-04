package me.patrycioss.empirewand

import me.patrycioss.empirewand.abilities.AbilityManager
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

        logger.info("[EmpireWand] The auditor of this wand has been initialized!")
    }

    @EventHandler
    fun onUseWand(playerInteractEvent: PlayerInteractEvent)
    {
        if (playerInteractEvent.hasItem() && playerInteractEvent.item!!.type == Material.BLAZE_ROD)
        {
            when (playerInteractEvent.action)
            {
                Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK ->
                    AbilityManager.currentAbility.activate(playerInteractEvent)


                Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK ->
                {
                    AbilityManager.nextAbility()
                }

                else -> {}
            }
        }


    }
}