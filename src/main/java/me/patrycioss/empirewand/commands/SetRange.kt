package me.patrycioss.empirewand.commands

import me.patrycioss.empirewand.EmpireWand
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


/**
 * Command to set the range of the users' empire wand
 */
class SetRange : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (sender is Player)
        {
            if (args.count() == 1)
            {
                val newRange : Int
                try
                {
                    newRange = args[0].toInt()
                }
                catch (e : NumberFormatException)
                {
                    sender.sendMessage("Value can only be a whole number in range 1-120!")
                    return false
                }

                return if (args[0].toInt() !in 1..120)
                {
                    sender.sendMessage("Value can only be from 1-120!")
                    false
                }
                else
                {
                    sender.sendMessage("Set value to $newRange")
                    EmpireWand.range = newRange
                    true
                }
            }
        }
        return false
    }
}