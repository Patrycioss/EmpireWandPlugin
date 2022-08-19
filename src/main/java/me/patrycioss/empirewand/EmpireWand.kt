package me.patrycioss.empirewand

import me.patrycioss.empirewand.commands.EmpireWandCommand
import me.patrycioss.empirewand.commands.SetEmpireWandExplosionPower
import me.patrycioss.empirewand.commands.SetEmpireWandRange
import org.bukkit.plugin.java.JavaPlugin

class EmpireWand : JavaPlugin()
{
    companion object {
        /**
         * Range the empire wand has, must be a value in range 1-120
         */
        var range : Int = 20

        /**
         * Explosion power of the empire wand, must have a value of 0 or higher
         */
        var explosionPower : Float = 4.0f

        private lateinit var empireWandListener : EmpireWandListener
    }

    override fun onEnable()
    {
        empireWandListener = EmpireWandListener(this)

        //Commands
        getCommand("setempirewandrange")?.setExecutor(SetEmpireWandRange())
        getCommand("setempirewandexplosionpower")?.setExecutor(SetEmpireWandExplosionPower())
        getCommand("empirewand")?.setExecutor(EmpireWandCommand())

        logger.info("PK empire!")
    }

    override fun onDisable()
    {
        logger.info("Goodbye cruel world! *dramatic noises*")
    }
}


