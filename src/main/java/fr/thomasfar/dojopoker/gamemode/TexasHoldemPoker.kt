package fr.thomasfar.dojopoker.gamemode

import fr.thomasfar.dojopoker.hands.Hand
import java.util.ArrayList

class TexasHoldemPoker : GamePoker() {
    var boards: MutableList<Hand> = ArrayList()

    init {
        println("\n\n▀█▀ █▀▀ ▀▄▀ ▄▀▄ █▀▀ █▄█ █▀█ █   █▀▄ █▀▀ █▄ ▄█ █▀█ █▀█ █▄▀ █▀▀ █▀█\n█  ██▄ █ █ █▀█ ▄██ █ █ █▄█ █▄▄ █▄▀ ██▄ █ ▀ █ █▀▀ █▄█ █ █ ██▄ █▀▄\n\n")
        players = super.selectPlayers()
    }


    fun stepFlop() {
        //3 cartes
        print("Flop")
    }

    fun stepTurn() {
        //1 cartes
        print("Turn")
    }

    fun stepRiver() {
        //1 cartes
        print("River")
    }

}