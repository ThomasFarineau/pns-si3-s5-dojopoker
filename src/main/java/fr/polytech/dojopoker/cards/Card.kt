package fr.polytech.dojopoker.cards

import fr.polytech.dojopoker.exceptions.CardValidException
import java.util.*

class Card(
    /**
     * Gets value.
     *
     * @return the card's value
     */
    val value: Int,
    /**
     * Gets color.
     *
     * @return the card's color
     */
    val color: CardColor
) : Comparable<Card> {


    /**
     * compares the value of two cards
     *
     * @param c the card to be compared
     * @return the difference between the two cards
     */
    override fun compareTo(c: Card): Int {
        return value - c.value
    }

    /**
     * @return String representation of the card
     */
    override fun toString(): String {
        val valueStr =
            if (CardName.enumFromValue(value) != null) CardName.enumFromValue(value).toString() else value.toString()
        return valueStr + "" + color.value + ""
    }


    /**
     * Gets string value.
     *
     * @return the string value
     */
    val stringValue: String
        get() = if (Objects.nonNull(CardName.enumFromValue(value))) {
            CardName.enumFromValue(value)!!.readName
        } else {
            value.toString()
        }

    /**
     * @param other the card to be compared
     * @return true if the cards are equal, false otherwise
     */
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Card -> Objects.nonNull(other) && other.value == value
            else -> false
        }
    }

    companion object {
        /**
         * checks if a card i valid
         *
         * @param n card value
         * @param c card color
         * @return true if valid
         * @throws CardValidException the card valid exception
         */
        @JvmStatic
        @Throws(CardValidException::class)
        fun isValidCard(n: Int, c: String): Boolean {
            if (n in 2..14 && CardColor.isValueCorrect(c)) return true
            throw CardValidException(n.toString() + c)
        }
    }
}