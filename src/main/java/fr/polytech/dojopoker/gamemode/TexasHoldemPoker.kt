package fr.polytech.dojopoker.gamemode

class TexasHoldemPoker : GamePoker() {
    init {
        println("\n\n▀█▀ █▀▀ ▀▄▀ ▄▀▄ █▀▀ █▄█ █▀█ █   █▀▄ █▀▀ █▄ ▄█ █▀█ █▀█ █▄▀ █▀▀ █▀█\n█  ██▄ █ █ █▀█ ▄██ █ █ █▄█ █▄▄ █▄▀ ██▄ █ ▀ █ █▀▀ █▄█ █ █ ██▄ █▀▄\n\n")
        players = super.selectPlayers()
    }
}