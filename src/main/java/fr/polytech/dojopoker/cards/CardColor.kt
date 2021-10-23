package fr.polytech.dojopoker.cards

import fr.polytech.dojopoker.GameController

enum class CardColor(val value: String, val readName: String) {
    CLUBS(GameController.lang["flush.clubs.2"], GameController.lang["flush.clubs.name"]),
    DIAMONDS(GameController.lang["flush.diamonds.2"], GameController.lang["flush.diamonds.name"]),
    HEART(GameController.lang["flush.hearts.2"], GameController.lang["flush.hearts.name"]),
    SPADES(GameController.lang["flush.spades.2"], GameController.lang["flush.spades.name"]);

    companion object {
        @JvmStatic
        fun enumFromValue(color: String): CardColor? {
            for (cardColor in values()) if (cardColor.value == color) return cardColor
            return null
        }

        @JvmStatic
        fun isValueCorrect(str: String): Boolean {
            return enumFromValue(str) != null
        }
    }
}