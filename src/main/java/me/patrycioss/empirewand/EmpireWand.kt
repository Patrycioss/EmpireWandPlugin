package me.patrycioss.empirewand

import org.bukkit.plugin.java.JavaPlugin

class EmpireWand : JavaPlugin()
{
    companion object {
        /**
         * Range the empire wand has, must be a value in range 1-120
         */
        @JvmField
        var range : Int = 20
        private lateinit var empireWandListener : EmpireWandListener;
    }

    init
    {
    }

    override fun onEnable()
    {
        Companion.empireWandListener = EmpireWandListener(this)

        //Commands
        getCommand("setrange")?.setExecutor(EmpireWandSetRange())


        logger.info("PK empire!")
    }

    override fun onDisable()
    {
        logger.info("Goodbye cruel world! *dramatic noises*")
    }





}


