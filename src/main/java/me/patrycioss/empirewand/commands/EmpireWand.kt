package me.patrycioss.empirewand.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemStack

class EmpireWand : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (args.count() == 1)
        {
            when (val target = Bukkit.getPlayer(args[0]))
            {
                null ->
                {
                    sender.sendMessage("Invalid target name")
                    return false
                }

                else ->
                {
                    if (target.isOnline)
                    {
                        val empireWand = ItemStack(Material.BLAZE_ROD,1)
                        val empireWandMeta = empireWand.itemMeta

                        val empireWandName : Component = Component.text("Empire Wand")
                        empireWandName.color(TextColor.color(0x8b0000))
                        empireWandName.decoration(TextDecoration.BOLD)

                        empireWandMeta.displayName(empireWandName)

                        Bukkit.getServer().logger.info(empireWand.itemMeta.displayName().toString())

                        target.inventory.addItem(empireWand)
                        return true
                    }
                    else
                    {
                        sender.sendMessage("Player is not online at the moment!")
                        return false
                    }
                }
            }
        }
        else return false
    }
}