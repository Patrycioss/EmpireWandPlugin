package me.patrycioss.empirewand

import me.patrycioss.empirewand.commands.EmpireWandCommand
import me.patrycioss.empirewand.commands.SetExplosionPower
import me.patrycioss.empirewand.commands.SetRange
import org.bukkit.plugin.java.JavaPlugin

class EmpireWand : JavaPlugin()
{
    companion object {
        /**
         * Range the empire wand has, must be a value in range 1-120
         */
        @JvmField
        var range : Int = 20
        var explosionPower : Float = 4.0f

        private lateinit var empireWandListener : EmpireWandListener
    }

    override fun onEnable()
    {
        empireWandListener = EmpireWandListener(this)

        //Commands
        getCommand("setrange")?.setExecutor(SetRange())
        getCommand("setexplosionpower")?.setExecutor(SetExplosionPower())
        getCommand("empirewand")?.setExecutor(EmpireWandCommand())

        logger.info("PK empire!")
    }

    override fun onDisable()
    {
        logger.info("Goodbye cruel world! *dramatic noises*")
    }
}


