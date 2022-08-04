package me.patrycioss.empirewand.abilities

import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerInteractEvent

object AbilityManager
{
    var currentAbility : Ability = Ability.EXPLOSION
    var currentIndex = 0


    enum class Ability
    {
        EXPLOSION
        {
            override fun activate(playerInteractEvent: PlayerInteractEvent)
            {
                Explosion().activate(playerInteractEvent)
            }
        },
        FIREBALL
        {
            override fun activate(playerInteractEvent: PlayerInteractEvent)
            {
                FireBall().activate(playerInteractEvent)
            }
        };

        abstract fun activate(playerInteractEvent: PlayerInteractEvent)
    }

    init
    {
        Bukkit.getServer().logger.info("[EmpireWand] AbilityManager initialized")
    }

    fun nextAbility()
    {
        currentIndex++

        if (currentIndex > Ability.values().size - 1) currentIndex = 0

        currentAbility = Ability.values()[currentIndex]
    }

    fun previousAbility()
    {
        currentIndex--

        if (currentIndex < 0) currentIndex = Ability.values().size - 1

        currentAbility = Ability.values()[currentIndex]
    }

}