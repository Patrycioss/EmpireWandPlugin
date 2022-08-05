package me.patrycioss.empirewand.commands

import me.patrycioss.empirewand.EmpireWand
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetExplosionPower : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (sender is Player)
        {
            if (args.count() == 1)
            {
                val newPower : Float
                try
                {
                    newPower = args[0].toFloat()
                }
                catch (e : NumberFormatException)
                {
                    sender.sendMessage("Value has to be a decimal or whole number higher or equal to zero")
                    return false
                }

                return if (args[0].toFloat() < 0)
                {
                    sender.sendMessage("Value has to be a decimal or whole number higher or equal to zero")
                    false
                }
                else
                {
                    sender.sendMessage("Set explosion power to $newPower")
                    EmpireWand.explosionPower = newPower
                    true
                }
            }
        }
        return false
    }
}