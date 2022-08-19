package me.patrycioss.empirewand.commands

import me.patrycioss.empirewand.EmpireWand
import me.patrycioss.empirewand.MessageGenerator
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


/**
 * Command to set the range of the users' empire wand
 */
class SetEmpireWandRange : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (sender is Player)
        {
            when (args.count())
            {
                1 ->
                {
                    val newRange : Int

                    try
                    {
                        newRange = args[0].toInt()
                    }
                    catch (e : NumberFormatException)
                    {
                        sender.sendMessage(MessageGenerator.generateWarningMessage("Range can only be a whole number in range 1-120!"))
                        return false
                    }

                    return if (newRange !in 1..120)
                    {
                        sender.sendMessage(MessageGenerator.generateWarningMessage("Range can only be from 1-120!"))
                        false
                    }
                    else
                    {
                        EmpireWand.range = newRange
                        sender.sendMessage(MessageGenerator.generateConfirmationMessage("Set range to $newRange"))
                        return true
                    }
                }

                0 -> sender.sendMessage(MessageGenerator.generateWarningMessage("No range given!"))

                else -> sender.sendMessage(MessageGenerator.generateWarningMessage("Too many arguments given!"))
            }
        }
        return false
    }
}