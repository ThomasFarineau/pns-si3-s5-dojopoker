package fr.polytech.dojopoker

import fr.polytech.dojopoker.cards.Card
import fr.polytech.dojopoker.cards.Card.Companion.isValidCard
import fr.polytech.dojopoker.cards.CardColor.Companion.enumFromValue
import fr.polytech.dojopoker.exceptions.CardFormatException
import fr.polytech.dojopoker.exceptions.HandSizeException
import java.util.*
import java.util.function.Consumer

internal class GameReader(private val game: GameController) {

    private fun inputConversion(cardRepresentation: String): List<Card> {
        val c: MutableList<Card> = ArrayList()
        var pos = 0
        val cardsReads = cardRepresentation.split(" ").toTypedArray()
        try {
            if (isCorrectHandSize(cardsReads.size)) for (s in cardsReads) {
                pos++
                val ci = s.split("((?=[A-Z&&[^VDRA]]))").toTypedArray()
                if (isValidCardFormat(ci.size, pos)) {
                    val value: Int = when (ci[0]) {
                        "V" -> 11
                        "D" -> 12
                        "R" -> 13
                        "A" -> 14
                        else -> ci[0].toInt()
                    }
                    if (isValidCard(value, ci[1])) game.deck.pickCard(Card(value, enumFromValue(ci[1])!!))
                        .let { c.add(it) }
                }
            }
        } catch (e: Exception) {
            // Cancel the remove of cards in the deck if there is an exception
            c.forEach(Consumer { card: Card? -> game.deck.addCard(card) })
            println(e.message)
        }
        return c
    }

    fun readingStandardInput() {
        val sc = Scanner(System.`in`)
        for (hand in game.hands) do hand.cards = insertHand(sc, hand.id) as MutableList<Card> while (!hand.isValid)
    }

    private fun insertHand(sc: Scanner, id: Int): List<Card> {
        print(GameController.lang["reader.input"].replace("{id}", "$id"))
        return inputConversion(sc.nextLine())
    }

    @Throws(CardFormatException::class)
    fun isValidCardFormat(i: Int, b: Int): Boolean {
        if (i == 2) return true
        throw CardFormatException(b)
    }

    @Throws(HandSizeException::class)
    fun isCorrectHandSize(i: Int): Boolean {
        if (i == cardsToRead) return true
        throw HandSizeException(cardsToRead, i)
    }

    companion object {
        var cardsToRead = 5
    }
}