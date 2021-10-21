package fr.polytech.dojopoker

import java.util.*

internal class GameController {
    @JvmField
    var deck = Deck()
    var hands: MutableList<Hand> = ArrayList()
    private var players: Int

    init {
        players = selectPlayers()
        (1..players).forEach { i -> hands.add(Hand(i)) }

        GameReader(this).readingStandardInput()

        val hand = winnerHand()
        println(getWinningMessage(hand))
    }

    private fun selectPlayers(): Int {
        val sc = Scanner(System.`in`)
        var players = 0
        do {
            println("How many hands will this game be played with? (between 2 and 10)")
            try {
                players = Integer.parseInt(sc.nextLine())
            } catch (e: Exception) {
                println("What you have input is not a number")
            }
        } while (players < 2 || players > 10)
        return players
    }

    private fun winnerHand(): Hand? {
        hands.sortWith(Collections.reverseOrder())
        for (i in 1..hands.size)
            if (hands[0].compareTo(hands[1]) == 0)
                return null
        return hands[0]
    }

    private fun getWinningMessage(hand: Hand?): String {
        if (Objects.isNull(hand)) {
            return "Equality"
        } else {
            val rankings = hand!!.ranking
            var toReturn = "La main ${hand.id} gagne avec ${rankings.readName}"
            when {
                rankings === HandRankings.PAIR || rankings === HandRankings.DOUBLEPAIR || rankings === HandRankings.BRELAN || rankings === HandRankings.SQUARE || rankings === HandRankings.FULL -> {
                    toReturn += " de ${hand.getCard(0).stringValue}"
                    if (rankings === HandRankings.DOUBLEPAIR || rankings === HandRankings.FULL)
                        toReturn += " et de ${hand.getCard(3).stringValue}"
                }
                rankings === HandRankings.HIGHESTCARD -> toReturn += " : ${hand.getCard(0).stringValue}"
                rankings === HandRankings.FLUSH -> toReturn += " : ${hand.getCard(0).color.readName}"
                rankings === HandRankings.STRAIGHT || rankings === HandRankings.STRAIGHTFLUSH -> toReturn += " : ${hand.cards}"
            }
            return toReturn
        }
    }


}