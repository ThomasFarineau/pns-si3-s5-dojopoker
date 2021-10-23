package fr.polytech.dojopoker.gamemode

import fr.polytech.dojopoker.Deck
import fr.polytech.dojopoker.GameController
import fr.polytech.dojopoker.hands.Hand
import java.util.*
import java.util.stream.IntStream

abstract class GamePoker internal constructor() {
    var players: Int = 0
    var deck = Deck()
    var hands: MutableList<Hand> = ArrayList()

    fun selectPlayers(): Int {
        val sc = Scanner(System.`in`)
        var players = 0
        do {
            println(GameController.lang["init.players.input"])
            try {
                players = sc.nextLine().toInt()
            } catch (e: Exception) {
                println(GameController.lang["init.players.input.error"])
            }
        } while (players < 2 || players > 10)
        IntStream.range(0, players).forEach { i: Int -> hands.add(Hand(i + 1)) }
        return players
    }


}