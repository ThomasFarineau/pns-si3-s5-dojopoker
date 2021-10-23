package fr.polytech.dojopoker.gamemode

import fr.polytech.dojopoker.GameController
import fr.polytech.dojopoker.cards.Card
import fr.polytech.dojopoker.cards.Card.Companion.isValidCard
import fr.polytech.dojopoker.cards.CardColor.Companion.enumFromValue
import fr.polytech.dojopoker.cards.CardName
import fr.polytech.dojopoker.exceptions.CardFormatException
import fr.polytech.dojopoker.exceptions.HandSizeException
import java.util.*
import java.util.function.Consumer

class DojoPoker : GamePoker() {
    private val cardsToRead = 5
    init {
        println("\n\n█▀▄ █▀█ ▀█▀ █▀█ █▀█ █▀█ █▄▀ █▀▀ █▀█\n█▄▀ █▄█ ▄█  █▄█ █▀▀ █▄█ █ █ ██▄ █▀▄\n\n")
        players = super.selectPlayers()
        readHands()
    }

    private fun readHands() {
        val sc = Scanner(System.`in`)
        for (hand in hands) do {
            print(GameController.lang["reader.input"].replace("{id}", "${hand.id}"))
            hand.cards = inputConversion(sc.nextLine()) as MutableList<Card>
        } while (!hand.isValid)
    }

    private fun inputConversion(cardRepresentation: String): List<Card> {
        val c: MutableList<Card> = ArrayList()
        var pos = 0
        val cardsReads = cardRepresentation.split(" ").toTypedArray()
        try {
            if (isCorrectHandSize(cardsReads.size)) for (s in cardsReads) {
                pos++
                val ci = s.split(Regex("((?=[A-Z&&[^${CardName.J.indicator}${CardName.Q.indicator}${CardName.K.indicator}${CardName.A.indicator}]]))")).toTypedArray()
                if (isValidCardFormat(ci.size, pos)) {
                    val value: Int = when (ci[0]) {
                        CardName.J.indicator -> CardName.J.value
                        CardName.Q.indicator -> CardName.Q.value
                        CardName.K.indicator -> CardName.K.value
                        CardName.A.indicator -> CardName.A.value
                        else -> ci[0].toInt()
                    }
                    if (isValidCard(value, ci[1])) deck.pickCard(Card(value, enumFromValue(ci[1])!!))
                        .let { c.add(it) }
                }
            }
        } catch (e: Exception) {
            // Cancel the remove of cards in the deck if there is an exception
            c.forEach(Consumer { card: Card ->
                deck.addCard(card)
            })
            println(e.message)
        }
        return c
    }

    //Classement des mains
    private fun rankHands() {

    }
    //Message de fins
    private fun endMessage() {

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
}