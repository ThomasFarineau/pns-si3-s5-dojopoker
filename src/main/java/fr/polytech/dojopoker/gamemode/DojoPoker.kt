package fr.polytech.dojopoker.gamemode

import fr.polytech.dojopoker.GameController.Companion.lang
import fr.polytech.dojopoker.cards.Card
import fr.polytech.dojopoker.cards.Card.Companion.isValidCard
import fr.polytech.dojopoker.cards.CardColor.Companion.enumFromValue
import fr.polytech.dojopoker.cards.CardName
import fr.polytech.dojopoker.exceptions.CardFormatException
import fr.polytech.dojopoker.exceptions.HandSizeException
import fr.polytech.dojopoker.hands.Hand
import fr.polytech.dojopoker.hands.HandRankings
import java.util.*
import java.util.function.Consumer

class DojoPoker : GamePoker() {
    private val cardsToRead = 5

    init {
        println("\n\n█▀▄ █▀█ ▀█▀ █▀█ █▀█ █▀█ █▄▀ █▀▀ █▀█\n█▄▀ █▄█ ▄█  █▄█ █▀▀ █▄█ █ █ ██▄ █▀▄\n\n")
        players = super.selectPlayers()
        readHands()
        println(getWinningMessage(winnerHand()))
    }

    private fun readHands() {
        val sc = Scanner(System.`in`)
        for (hand in hands) do {
            print(lang["reader.input"].replace("{id}", "${hand.id}"))
            val nLine = sc.nextLine()
            when {
                nLine.equals(lang["reader.random"]) -> hand.cards = randomCards()
                else -> hand.cards = inputConversion(nLine)
            }
        } while (!hand.isValid)
    }

    private fun randomCards(): MutableList<Card> {
        val toReturn: MutableList<Card> = ArrayList()
        repeat((1..cardsToRead).count()) { toReturn.add(deck.pickRandomCard()) }
        println(toReturn.toString())
        return toReturn
    }

    private fun inputConversion(cardRepresentation: String): MutableList<Card> {
        val c: MutableList<Card> = ArrayList()
        var pos = 0
        val cardsReads = cardRepresentation.split(" ").toTypedArray()
        try {
            if (isCorrectHandSize(cardsReads.size)) for (s in cardsReads) {
                pos++
                val ci =
                    s.split(Regex("((?=[A-Z&&[^${CardName.J.indicator}${CardName.Q.indicator}${CardName.K.indicator}${CardName.A.indicator}]]))"))
                        .toTypedArray()
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
            c.forEach(Consumer { card: Card ->
                deck.addCard(card)
            })
            println(e.message)
        }
        return c
    }

    private fun winnerHand(): Hand? {
        hands.sortWith(Collections.reverseOrder())
        for (i in 1..hands.size)
            if (hands[0].compareTo(hands[1]) == 0)
                return null
        return hands[0]
    }

    private fun getWinningMessage(hand: Hand?): String {
        when {
            Objects.isNull(hand) -> return lang["end.equality"]
            else -> {
                val rankings = hand!!.ranking
                var toReturn =
                    lang["end.win.message"].replace("{id}", "${hand.id}").replace("{ranking}", rankings.readName)
                when {
                    rankings === HandRankings.PAIR || rankings === HandRankings.TWO_PAIR || rankings === HandRankings.THREE_OF_A_KIND || rankings === HandRankings.FOUR_OF_A_KIND || rankings === HandRankings.FULL_HOUSE -> {
                        toReturn += lang["end.win.message.of"] + hand.cards[0].stringValue()
                        if (rankings === HandRankings.TWO_PAIR || rankings === HandRankings.FULL_HOUSE) {
                            println(hand.cards[3].stringValue())
                            toReturn += " " + lang["end.win.message.of.of"] + hand.cards[3].stringValue()

                        }
                    }
                    rankings === HandRankings.HIGH_CARD -> toReturn += ": ${hand.cards[0].stringValue()}"
                    rankings === HandRankings.FLUSH -> toReturn += ": ${hand.cards[0].color.readName}"
                    rankings === HandRankings.STRAIGHT || rankings === HandRankings.STRAIGHT_FLUSH -> toReturn += ": ${hand.cards}"
                }
                return toReturn
            }
        }
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