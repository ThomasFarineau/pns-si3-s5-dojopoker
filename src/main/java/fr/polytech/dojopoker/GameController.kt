package fr.polytech.dojopoker

import fr.polytech.dojopoker.exceptions.HandNumbersException
import java.util.*

/**
 * The type Game controller.
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
internal class GameController(players: Int, reader: Boolean) {
    @JvmField
    var deck = Deck()
    var hands: MutableList<Hand> = ArrayList()

    /**
     * This method allows to print on the standard output the message of victory or equality according to the hands of the game
     *
     * Don't forget that there are only 2 hands normally, that's why the verification is only done on the first two of the list
     *
     * @return equality or the message of victory
     */
    fun whoIsTheWinner(): String {
        hands.sortWith(Collections.reverseOrder())
        return if (hands[0].compareTo(hands[1]) == 0) {
            "Egalite"
        } else getWinningMessage(hands[0])
    }

    /**
     * This method return the win message after the hand comparison via the whoIsTheWinner method
     *
     * @param hand the hand from which we want to retrieve the information to return it
     * @return the message of victory according to rankings and hand
     * @see GameController.whoIsTheWinner
     */
    fun getWinningMessage(hand: Hand): String {
        val rankings = hand.ranking
        var toReturn = "La main " + hand.id + " gagne avec " + rankings.readName
        when {
            rankings === HandRankings.PAIR || rankings === HandRankings.DOUBLEPAIR || rankings === HandRankings.BRELAN || rankings === HandRankings.SQUARE || rankings === HandRankings.FULL -> {
                toReturn += " de " + hand.getCard(0).stringValue
                if (rankings === HandRankings.DOUBLEPAIR || rankings === HandRankings.FULL) toReturn += " et de " + hand.getCard(
                    3
                ).stringValue
            }
            rankings === HandRankings.HIGHESTCARD -> {
                toReturn += " : " + hand.getCard(0).stringValue
            }
            rankings === HandRankings.FLUSH -> {
                toReturn += " : " + hand.getCard(0).color.readName
            }
            rankings === HandRankings.STRAIGHT || rankings === HandRankings.STRAIGHTFLUSH -> {
                toReturn += " : " + hand.cards
            }
        }
        return toReturn
    }

    /**
     * Constructor with two parameters, the number of hands (for abstractions) and whether it should use the reader or not (for unit tests)
     *
     * Note that the id of the hands will start at 1 until players for the display
     *
     * @param players The number of hands in our game ( usually 2 according to the rules of the project)
     * @param reader  If the program has to read the hands from the stdin (for JUNIT tests)
     * @throws HandNumbersException the hand numbers exception
     */
    init {
        if (players <= 1) {
            throw HandNumbersException()
        }
        (1..players).forEach { i -> hands.add(Hand(i)) }
        if (reader) {
            GameReader(this).readingStandardInput()
            println(whoIsTheWinner())
        }
    }
}