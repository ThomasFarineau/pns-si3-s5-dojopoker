package fr.polytech.dojopoker.gamemode

class TexasHoldemPoker : GamePoker() {


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