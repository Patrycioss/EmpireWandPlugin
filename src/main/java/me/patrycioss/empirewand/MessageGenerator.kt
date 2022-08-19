package me.patrycioss.empirewand

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

class MessageGenerator
{
    companion object
    {
        fun generateErrorMessage(message : String) : TextComponent
        {
            return Component.text("")
                .color(TextColor.color(0x8b0000))
                .decoration(TextDecoration.BOLD, true)
                .append(Component.text(message))
        }

        fun generateWarningMessage(message : String) : TextComponent
        {
            return Component.text("")
                .color(TextColor.color(0xFFCC00))
                .decoration(TextDecoration.BOLD,true)
                .append(Component.text(message))
        }

        fun generateConfirmationMessage(message : String) : TextComponent
        {
            return Component.text("")
                .color(TextColor.color(0x00ff00))
                .decoration(TextDecoration.BOLD,true)
                .append(Component.text(message))
        }
    }
}