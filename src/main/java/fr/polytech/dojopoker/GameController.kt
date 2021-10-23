package fr.polytech.dojopoker

import fr.polytech.dojopoker.gamemode.DojoPoker
import fr.polytech.dojopoker.gamemode.GamePoker
import fr.polytech.dojopoker.gamemode.TexasHoldemPoker
import java.util.*

internal class GameController {
    var gamemode: GamePoker

    init {
        lang.locale = selectLang()
        gamemode = selectGamemode()
    }

    private fun selectLang(): String {
        val sc = Scanner(System.`in`)
        var newLang: String
        do {
            println(lang["init.locale.select"].replace("{list}", lang.locales.toString()))
            newLang = sc.nextLine()
            if (!lang.locales.contains(newLang)) println(lang["init.locale.select.error"])
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