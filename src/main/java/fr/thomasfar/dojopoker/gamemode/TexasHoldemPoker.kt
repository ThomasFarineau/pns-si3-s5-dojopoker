package fr.thomasfar.dojopoker.gamemode

import fr.thomasfar.dojopoker.cards.Card
import fr.thomasfar.dojopoker.hands.Hand

class TexasHoldemPoker : GamePoker() {
    var boards: MutableList<Card> = ArrayList()

    init {
        println("\n\n▀█▀ █▀▀ ▀▄▀ ▄▀▄ █▀▀ █▄█ █▀█ █   █▀▄ █▀▀ █▄ ▄█ █▀█ █▀█ █▄▀ █▀▀ █▀█\n █  ██▄ █ █ █▀█ ▄██ █ █ █▄█ █▄▄ █▄▀ ██▄ █ ▀ █ █▀▀ █▄█ █ █ ██▄ █▀▄\n\n")
        players = super.selectPlayers()

        var winner: Hand? = null

        while (winner == null) {
            stepFlop()
            stepTurn()
            stepRiver()
            println(boards)
            winner = hands[0]
        }
    }

    fun stepFlop() {
        boards.add(deck.pickRandomCard())
        boards.add(deck.pickRandomCard())
        boards.add(deck.pickRandomCard())
        print("Flop")
    }

    fun stepTurn() {
        boards.add(deck.pickRandomCard())
        print("Turn")
    }

    fun stepRiver() {
        boards.add(deck.pickRandomCard())
        print("River")
    }

}