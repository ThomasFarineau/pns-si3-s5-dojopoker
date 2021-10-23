package fr.polytech.dojopoker

import fr.polytech.dojopoker.gamemode.DojoPoker
import fr.polytech.dojopoker.gamemode.GamePoker
import fr.polytech.dojopoker.gamemode.TexasHoldemPoker
import fr.polytech.dojopoker.hands.Hand
import fr.polytech.dojopoker.hands.HandRankings
import java.util.*
import kotlin.collections.ArrayList

internal class GameController {
    var gamemode: GamePoker
    init {
        lang.locale = selectLang()
        gamemode = selectGamemode()

        //GameReader(this).readingStandardInput()

        //println(getWinningMessage(winnerHand()))
    }

    private fun selectLang(): String {
        val sc = Scanner(System.`in`)
        var newLang: String
        do {
            println(lang["init.locale.select"].replace("{list}", lang.locales.toString()))
            newLang = sc.nextLine()
            if(!lang.locales.contains(newLang)) println(lang["init.locale.select.error"])
        } while (!lang.locales.contains(newLang))

        return newLang
    }

    private fun selectGamemode(): GamePoker {
        val sc = Scanner(System.`in`)
        var mode: String
        val modes = ArrayList<String>()
        modes.add("Dojo")
        modes.add("TexasHoldem")
        do {
            println(lang["init.gamemode.input"].replace("{list}", modes.toString()))
            mode = sc.nextLine()
            if(!modes.contains(mode)) println(lang["init.gamemode.input.error"])
        } while (!modes.contains(mode))
        return when (mode) {
            "TexasHoldem" -> TexasHoldemPoker()
            "Dojo" -> DojoPoker()
            else -> DojoPoker()
        }
    }

    /**
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
                var toReturn = lang["end.win.message"].replace("{id}", "${hand.id}").replace("{ranking}", rankings.readName)
                when {
                    rankings === HandRankings.PAIR || rankings === HandRankings.TWO_PAIR || rankings === HandRankings.THREE_OF_A_KIND || rankings === HandRankings.FOUR_OF_A_KIND || rankings === HandRankings.FULL_HOUSE -> {
                        toReturn += lang["end.win.message.of"] + hand.getCard(0).stringValue
                        if (rankings === HandRankings.TWO_PAIR || rankings === HandRankings.FULL_HOUSE)
                            toReturn += lang["end.win.message.of.of"] + {hand.getCard(3).stringValue}
                    }
                    rankings === HandRankings.HIGH_CARD -> toReturn += ": ${hand.getCard(0).stringValue}"
                    rankings === HandRankings.FLUSH -> toReturn += ": ${hand.getCard(0).color.readName}"
                    rankings === HandRankings.STRAIGHT || rankings === HandRankings.STRAIGHT_FLUSH -> toReturn += ": ${hand.cards}"
                }
                return toReturn
            }
        }
    }
        **/
    companion object {
        val lang = Lang()
    }

}