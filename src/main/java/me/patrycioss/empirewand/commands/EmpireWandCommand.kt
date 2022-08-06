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
                        target.inventory.addItem(empireWandItem)
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