package fr.thomasfar.dojopoker.cards

import fr.thomasfar.dojopoker.GameController

internal enum class CardName(val value: Int, val indicator: String, val readName: String) {
    J(11, GameController.lang["card.11.1"], GameController.lang["card.11.name"]),
    Q(12, GameController.lang["card.12.1"], GameController.lang["card.12.name"]),
    K(13, GameController.lang["card.13.1"], GameController.lang["card.13.name"]),
    A(14, GameController.lang["card.14.1"], GameController.lang["card.14.name"]);

    companion object {
        @JvmStatic
        fun enumFromValue(value: Int): CardName? {
            return values().firstOrNull { it.value == value }
        }

    }

}