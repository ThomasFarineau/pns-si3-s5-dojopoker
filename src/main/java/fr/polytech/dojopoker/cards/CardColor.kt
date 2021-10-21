package fr.polytech.dojopoker.cards

/**
 * enum of possible card color
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
enum class CardColor(val value: String, val readName: String) {
    /**
     * Trefle card color.
     */
    TREFLE("Tr", "Trèfle"),

    /**
     * Pique card color.
     */
    PIQUE("Pi", "Pique"),

    /**
     * Coeur card color.
     */
    COEUR("Co", "Coeur"),

    /**
     * Carreau card color.
     */
    CARREAU("Ca", "Carreau");

    companion object {
        /**
         * this function allows to get an enum from its value
         *
         * @param color the String value of the enum
         * @return the enum matching the input value
         */
        @JvmStatic
        fun enumFromValue(color: String): CardColor? {
            for (cardColor in values()) if (cardColor.value == color) return cardColor
            return null
        }

        /**
         * this function checks if the given string corresponds to an enum
         *
         * @param str string to check
         * @return true if it corresponds to an enum false otherwise
         */
        @JvmStatic
        fun isValueCorrect(str: String): Boolean {
            return enumFromValue(str) != null
        }


    }
}