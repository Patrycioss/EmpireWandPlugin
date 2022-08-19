package me.patrycioss.empirewand

import me.patrycioss.empirewand.abilities.AbilityManager
import me.patrycioss.empirewand.commands.EmpireWandCommand
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.PlayerInventory
import java.util.*
import java.util.logging.Logger


class EmpireWandListener(private val empireWand: EmpireWand) : Listener
{
    private val logger: Logger = Bukkit.getLogger()

    private val playerDropItemOnTick = mutableListOf<UUID>()
    private val playerInventoryOpen = hashMapOf<UUID,Boolean>()

    init
    {
        empireWand.server.pluginManager.registerEvents(this, empireWand)

        logger.info("[EmpireWand] The auditor of this wand has been initialized!")
    }

    @EventHandler
    fun onItemDrop(dropItemEvent: PlayerDropItemEvent)
    {
        registerItemDropper(dropItemEvent.player)
    }

    @EventHandler
    fun onInventoryOpen(openInventoryEvent: InventoryOpenEvent)
    {
        logger.info("haa")
        if (openInventoryEvent.inventory is PlayerInventory)
        {
            Bukkit.getServer().logger.info("ha")
            playerInventoryOpen[openInventoryEvent.viewers[0].uniqueId] = true
        }
    }

    @EventHandler
    fun onInventoryClose(closeInventoryEvent: InventoryCloseEvent)
    {
        if (closeInventoryEvent.inventory is PlayerInventory)
        {
            playerInventoryOpen.remove(closeInventoryEvent.viewers[0].uniqueId)
        }
    }

    @EventHandler
    fun onPlayerQuit(playerQuitEvent: PlayerQuitEvent)
    {
        playerInventoryOpen.remove(playerQuitEvent.player.uniqueId)
    }


    /**
     * Registers players which have dropped an item and removes them from the registry after an appropriate amount of time
     */
    private fun registerItemDropper(player: Player)
    {
        playerDropItemOnTick.add(player.uniqueId)

        Bukkit.getServer().scheduler.runTaskLater(empireWand,

            Runnable
        {
            playerDropItemOnTick.remove(player.uniqueId)
        },
            2L)
    }

    /**
     * Handles general player interactions with the wand
     */
    @EventHandler
    fun onUseWand(playerInteractEvent: PlayerInteractEvent)
    {
        //Make sure the ability doesn't trigger because of dropping items
        if (playerDropItemOnTick.contains(playerInteractEvent.player.uniqueId)) return

        //Make sure wand can't be used when player inventory is opens
        if (playerInventoryOpen[playerInteractEvent.player.uniqueId] == true) return


        if (playerInteractEvent.hasItem() && playerInteractEvent.item!!.type == Material.BLAZE_ROD)
        {
            if (playerInteractEvent.item!!.hasItemMeta() && playerInteractEvent.item!!.itemMeta.hasDisplayName())
            {
                if (playerInteractEvent.item!!.itemMeta.displayName() == EmpireWandCommand.displayName)
                {
                    when (playerInteractEvent.action)
                    {
                        Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK ->
                            AbilityManager.getCurrentAbility(playerInteractEvent).activate(playerInteractEvent)


                        Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK ->
                        {
                            AbilityManager.nextAbility(playerInteractEvent)
                        }

                        else ->
                        {
                        }
                    }
                }
            }
        }
    }
}


