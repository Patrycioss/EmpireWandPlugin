package me.patrycioss.empirewand.commands

import me.patrycioss.empirewand.MessageGenerator
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack




class EmpireWandCommand : CommandExecutor
{
    companion object
    {
        val displayName = createDisplayName()
        val empireWandItem = createEmpireWandItem()



        private fun createEmpireWandItem() : ItemStack
        {
            val empireWand = ItemStack(Material.BLAZE_ROD,1)
            val itemMeta = empireWand.itemMeta

            itemMeta.displayName(displayName.asComponent())

            empireWand.itemMeta = itemMeta
            return empireWand
        }

        private fun createDisplayName() : Component
        {
            val empireWandName : Component = Component.text("")
                .color(TextColor.color(0x8b0000))
                .decoration(TextDecoration.BOLD, true)
                .append(Component.text("Empire Wand"))

            return empireWandName.asComponent()
        }
    }



    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        val target: Player

        //Set target to sender if no target given
        if (args.isEmpty())
        {
            if (sender is Player)
                target = sender

            else return false
        }

        //else make sure target is valid
        else
        {
            val player = Bukkit.getPlayer(args[0])

            if (player == null)
            {
                sender.sendMessage(MessageGenerator.generateWarningMessage("Invalid Target!"))
                return false
            }
            else target = player
        }

        //Make sure target is online
        if (target.isOnline)
        {
            target.inventory.addItem(empireWandItem)
            return true
        }

        else
        {
            sender.sendMessage(MessageGenerator.generateWarningMessage("Player is not online at the moment!"))
            return false
        }
    }
}