package fr.polytech.dojopoker

import fr.polytech.dojopoker.gamemode.*
import java.util.*

internal class GameController {
    var gamemode: GamePoker

    init {
        lang.locale = setLang()
        gamemode = setGameMode()
    }

    private fun setLang(): String {
        val sc = Scanner(System.`in`)
        var newLang: String
        do {
            println(lang["init.locale.select"].replace("{list}", lang.locales.toString()))
            newLang = sc.nextLine()
            if (!lang.locales.contains(newLang)) println(lang["init.locale.select.error"])
        } while (!lang.locales.contains(newLang))

        return newLang
    }

    private fun setGameMode(): GamePoker {
        val sc = Scanner(System.`in`)
        var mode: String
        val modes = ArrayList<String>()
        modes.add("Dojo")
        modes.add("TexasHoldem")
        do {
            println(lang["init.gamemode.input"].replace("{list}", modes.toString()))
            mode = sc.nextLine()
            if (!modes.contains(mode)) println(lang["init.gamemode.input.error"])
        } while (!modes.contains(mode))
        return when (mode) {
            "TexasHoldem" -> TexasHoldemPoker()
            "Dojo" -> DojoPoker()
            else -> DojoPoker()
        }
    }

    companion object {
        val lang = Lang()
    }

}