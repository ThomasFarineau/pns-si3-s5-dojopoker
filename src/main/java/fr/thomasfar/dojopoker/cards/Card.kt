package fr.thomasfar.dojopoker.cards

import fr.thomasfar.dojopoker.exceptions.CardExistException
import java.util.*

class Card(val value: Int, val color: CardColor) : Comparable<Card> {
    override fun compareTo(other: Card): Int {
        return value - other.value
    }

    override fun toString(): String {
        return if (CardName.enumFromValue(value) != null) "${CardName.enumFromValue(value).toString()}${color.value}"
        else "$value${color.value}"
    }

    fun stringValue(): String {
        return if (CardName.enumFromValue(value) != null) CardName.enumFromValue(value)!!.readName else "$value"
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Card) Objects.nonNull(other) && other.value == value
        else false
    }

    override fun hashCode(): Int {
        return 31 * value + color.hashCode()
    }

    companion object {
        @JvmStatic
        @Throws(CardExistException::class)
        fun isValidCard(n: Int, c: String): Boolean {
            if (n in 2..14 && CardColor.isValueCorrect(c)) return true
            throw CardExistException(n.toString() + c)
        }
    }
}