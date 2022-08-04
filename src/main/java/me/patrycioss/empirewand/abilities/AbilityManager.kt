package me.patrycioss.empirewand.abilities

import net.kyori.adventure.identity.Identity
import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerInteractEvent

object AbilityManager
{
    private val playerAbilityIndexMap = hashMapOf<Identity, Pair<Int, Ability>>()

    fun getCurrentAbility(playerInteractEvent: PlayerInteractEvent) : Ability
    {
        val playerIdentity = playerInteractEvent.player.identity()

        when (val pair = playerAbilityIndexMap[playerIdentity])
        {
            null ->
            {
                playerAbilityIndexMap[playerIdentity] = Pair(0, Ability.values()[0])
                return Ability.values()[0]
            }

            else -> return pair.second
        }
    }

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

    fun nextAbility(playerInteractEvent: PlayerInteractEvent)
    {
        val playerIdentity = playerInteractEvent.player.identity()

        when (val pair = playerAbilityIndexMap[playerIdentity])
        {
            null -> playerAbilityIndexMap[playerIdentity] = Pair(0, Ability.values()[0])

            else ->
            {
                if (pair.first + 1 > Ability.values().size - 1)
                    playerAbilityIndexMap[playerIdentity] = Pair(0, Ability.values()[0])

                else playerAbilityIndexMap[playerIdentity] = Pair(pair.first + 1, Ability.values()[pair.first + 1])
            }
        }
    }

    fun previousAbility(playerInteractEvent: PlayerInteractEvent)
    {
        val playerIdentity = playerInteractEvent.player.identity()

        when (val pair = playerAbilityIndexMap[playerIdentity])
        {
            null -> playerAbilityIndexMap[playerIdentity] = Pair(0, Ability.values()[0])

            else ->
            {
                if (pair.first - 1 < 0)
                    playerAbilityIndexMap[playerIdentity] =
                        Pair(Ability.values().size - 1, Ability.values()[Ability.values().size - 1])

                else playerAbilityIndexMap[playerIdentity] = Pair(pair.first - 1, Ability.values()[pair.first - 1])
            }
        }
    }

}