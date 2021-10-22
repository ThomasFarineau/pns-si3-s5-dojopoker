package fr.polytech.dojopoker.cards

import fr.polytech.dojopoker.exceptions.CardValidException
import java.util.*

class Card(val value: Int, val color: CardColor) : Comparable<Card> {
    override fun compareTo(other: Card): Int {
        return value - other.value
    }

    override fun toString(): String {
        val valueStr =
            if (CardName.enumFromValue(value) != null) CardName.enumFromValue(value).toString() else value.toString()
        return valueStr + "" + color.value + ""
    }

    val stringValue: String
        get() = if (Objects.nonNull(CardName.enumFromValue(value))) {
            CardName.enumFromValue(value)!!.readName
        } else {
            value.toString()
        }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Card -> Objects.nonNull(other) && other.value == value
            else -> false
        }
    }

    companion object {
        @JvmStatic
        @Throws(CardValidException::class)
        fun isValidCard(n: Int, c: String): Boolean {
            if (n in 2..14 && CardColor.isValueCorrect(c)) return true
            throw CardValidException(n.toString() + c)
        }
    }
}