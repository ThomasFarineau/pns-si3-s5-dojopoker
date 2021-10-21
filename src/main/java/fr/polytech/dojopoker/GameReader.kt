package fr.polytech.dojopoker

import fr.polytech.dojopoker.cards.Card
import fr.polytech.dojopoker.cards.Card.Companion.isValidCard
import fr.polytech.dojopoker.cards.CardColor.Companion.enumFromValue
import fr.polytech.dojopoker.exceptions.CardFormatException
import fr.polytech.dojopoker.exceptions.HandSizeException
import java.util.*
import java.util.function.Consumer

/**
 * The type Game reader.
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
internal class GameReader (private val game: GameController) {
    /**
     * The function converts a String of cards in a list of cards.
     *
     * @param cardRepresentation string enter by the user
     * @return List of cards
     */
    fun inputConversion(cardRepresentation: String): List<Card> {
        val c: MutableList<Card> = ArrayList()
        var pos = 0
        val cardsReads = cardRepresentation.split(" ").toTypedArray()
        try {
            if (isCorrectHandSize(cardsReads.size)) for (s in cardsReads) {
                pos++
                val ci = s.split("((?=[A-Z&&[^VDRA]]))").toTypedArray()
                if (isValidCardFormat(ci.size, pos)) {
                    val value: Int = when (ci[0]) {
                        "V" -> 11
                        "D" -> 12
                        "R" -> 13
                        "A" -> 14
                        else -> ci[0].toInt()
                    }
                    if (isValidCard(value, ci[1])) game.deck.pickCard(Card(value, enumFromValue(ci[1])!!))
                        ?.let { c.add(it) }
                }
            }
        } catch (e: Exception) {
            // Cancel the remove of cards in the deck if there is an exception
            c.forEach(Consumer { card: Card? -> game.deck.addCard(card) })
            println(e.message)
        }
        return c
    }

    /**
     * The function fills the n hands of Game with the data of the user.
     */
    fun readingStandardInput() {
        val sc = Scanner(System.`in`)
        for (hand in game.hands) do hand.cards = insertHand(sc, hand.id) as MutableList<Card> while (!hand.isValid)
    }

    /**
     * The function reads the card entered by the user, then returns a list of cards.
     *
     * @param sc object of type Scanner
     * @param id number of the hand in Game
     * @return a list of card
     */
    fun insertHand(sc: Scanner, id: Int): List<Card> {
        print("Main $id: ")
        return inputConversion(sc.nextLine())
    }

    /**
     * Is valid card format boolean.
     *
     * @param i the size of the card information
     * @param b the card position for the error message
     * @return true if the i is equals to 2
     * @throws CardFormatException the card format not valid exception
     */
    @Throws(CardFormatException::class)
    fun isValidCardFormat(i: Int, b: Int): Boolean {
        if (i == 2) return true
        throw CardFormatException(b)
    }

    /**
     * Is correct hand size boolean.
     *
     * @param i the size of the hand
     * @return true if the i is equals to cardsToRead
     * @throws HandSizeException the hand size exception
     */
    @Throws(HandSizeException::class)
    fun isCorrectHandSize(i: Int): Boolean {
        if (i == cardsToRead) return true
        throw HandSizeException(cardsToRead, i)
    }

    override fun equals(obj: Any?): Boolean {
        if (obj === this) return true
        if (obj == null || obj.javaClass != this.javaClass) return false
        val that = obj as GameReader
        return game == that.game
    }

    override fun hashCode(): Int {
        return Objects.hash(game)
    }

    companion object {
        /**
         * This class is used for read the cards entered by the user.
         * cardsToRead corresponds to the number of card to read
         */
        var cardsToRead = 5
    }
}