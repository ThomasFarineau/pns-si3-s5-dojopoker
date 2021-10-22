package fr.polytech.dojopoker

import java.util.*

internal class GameController {
    @JvmField
    var deck = Deck()
    var hands: MutableList<Hand> = ArrayList()
    private var players: Int

    init {
        lang.locale = "FR"

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
            println(lang["init.players.input"])
            try {
                players = Integer.parseInt(sc.nextLine())
            } catch (e: Exception) {
                println(lang["init.players.input.error"])
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
            return lang["end.equality"]
        } else {
            val rankings = hand!!.ranking
            var toReturn = "La main ${hand.id} gagne avec ${rankings.readName}"
            when {
                rankings === HandRankings.PAIR || rankings === HandRankings.TWO_PAIR || rankings === HandRankings.THREE_OF_A_KIND || rankings === HandRankings.FOUR_OF_A_KIND || rankings === HandRankings.FULL_HOUSE -> {
                    toReturn += " de ${hand.getCard(0).stringValue}"
                    if (rankings === HandRankings.TWO_PAIR || rankings === HandRankings.FULL_HOUSE)
                        toReturn += " et de ${hand.getCard(3).stringValue}"
                }
                rankings === HandRankings.HIGH_CARD -> toReturn += " : ${hand.getCard(0).stringValue}"
                rankings === HandRankings.FLUSH -> toReturn += " : ${hand.getCard(0).color.readName}"
                rankings === HandRankings.STRAIGHT || rankings === HandRankings.STRAIGHT_FLUSH -> toReturn += " : ${hand.cards}"
            }
            return toReturn
        }
    }

    companion object {
        val lang = Lang()
    }

}