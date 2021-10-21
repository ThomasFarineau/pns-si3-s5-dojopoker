package fr.polytech.dojopoker

import fr.polytech.dojopoker.exceptions.HandNumbersException
import java.lang.Boolean
import kotlin.Array
import kotlin.String

/**
 * Program execution class, it simply makes a call to the Game class
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
internal object Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    @JvmStatic
    fun main(args: Array<String>) {
        var players = 2
        var reader = true
        if (args.isNotEmpty()) {
            players = args[0].toInt()
            if (args.size == 2) reader = Boolean.getBoolean(args[1])
        }
        try {
            GameController(players, reader)
        } catch (e: HandNumbersException) {
            println(e.message)
        }
    }}