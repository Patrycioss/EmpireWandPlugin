package me.patrycioss.empirewand

import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin
import java.lang.Exception

class EmpireWand : JavaPlugin()
{
    companion object {
        /**
         * Range the empire wand has, must be a value in range 1-120
         */
        @JvmField
        var range : Int = 20

        @JvmField
        var world : World? = Bukkit.getServer().getWorld("world")
    }

    private lateinit var empireWandListener : EmpireWandListener;

    init
    {
        if (world == null) throw Exception("Couldn't find current world :/")
    }

    override fun onEnable()
    {
        empireWandListener = EmpireWandListener(this)

        //Commands
        getCommand("setrange")?.setExecutor(EmpireWandSetRange())


        logger.info("PK empire!")
    }

    override fun onDisable()
    {
        logger.info("Goodbye cruel world! *dramatic noises*")
    }





}


