package fr.polytech.dojopoker.cards

/**
 * enum of card values corresponding to figures
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
internal enum class CardName(private val value: Int, val readName: String) {
    /**
     * V card name.
     */
    V(11, "Valet"),

    /**
     * D card name.
     */
    D(12, "Dame"),

    /**
     * R card name.
     */
    R(13, "Roi"),

    /**
     * A card name.
     */
    A(14, "As");


    companion object {
        /**
         * Enum from value card name.
         *
         * @param value the value
         * @return the card name
         */
        @JvmStatic
        fun enumFromValue(value: Int): CardName? {
            for (cardName in values()) if (cardName.value == value) return cardName
            return null
        }


    }


}