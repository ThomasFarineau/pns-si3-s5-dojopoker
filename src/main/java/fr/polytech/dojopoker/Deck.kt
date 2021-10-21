package fr.polytech.dojopoker

import fr.polytech.dojopoker.cards.Card
import fr.polytech.dojopoker.cards.CardColor
import fr.polytech.dojopoker.exceptions.CardTakenException
import java.util.*

/**
 * The type Deck.
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
internal class Deck @JvmOverloads constructor(i: Int = 1) {
    /**
     * The list of cards in the deck
     * The Map uses two objects, first the **card** as a key and an **integer** to tell how many occurrences of that card there are in the deck
     *
     * @see Card
     */
    private val cardIntegerMap: MutableMap<Card?, Int> = LinkedHashMap()
    private var size = 0

    /**
     * Deck size int.
     *
     * @return the int
     */
    fun deckSize(): Int {
        return cardIntegerMap.size * size
    }

    /**
     * Initializes the i*52-card deck according to the colours and 13 values of each card
     *
     * @param i the number of occurrences of each card
     */
    fun init(i: Int) {
        size = i
        cardIntegerMap.clear()
        for (value in CardColor.values()) for (j in 2..14) cardIntegerMap[Card(j, value)] = i
    }

    /**
     * This function allows you to retrieve a card, thus removing it from the deck and indicating that it has been removed
     *
     * @param c the card to be picked
     * @return the card if it could be removed from the deck, otherwise null
     * @throws CardTakenException the card taken exception
     */
    @Throws(CardTakenException::class)
    fun pickCard(c: Card?): Card? {
        val c = getCard(c)
        if (!isCardPicked(c)) {
            cardIntegerMap.replace(c, cardIntegerMap[c]!! - 1)
            return c
        }
        throw c?.let { CardTakenException(it) }!!
    }

    /**
     * This function allows to check if a card has been recovered by a player "hand" or not
     *
     * @param c the card to check
     * @return true if the card has been taken, otherwise false
     */
    fun isCardPicked(c: Card?): Boolean {
        return cardIntegerMap[getCard(c)] == 0
    }

    /**
     * This function allows to retrieve an existing card from the deck as well as to make an exception if the card entered does not exist (which is normally impossible)
     *
     * @param c the card to be retrieved
     * @return the recovered card
     */
    fun getCard(c: Card?): Card? {
        if (Objects.nonNull(c)) for ((key) in cardIntegerMap) if (key!!.value == c!!.value && key.color == c.color) return key
        throw IllegalArgumentException("The card $c is not part of the deck")
    }

    /**
     * Add card.
     *
     * @param c the c card to add
     */
    fun addCard(c: Card?) {
        if (isCardPicked(c)) cardIntegerMap.replace(c, cardIntegerMap[c]!! + 1)
    }

    /**
     * Pick random card card.
     *
     * @return the card
     * @throws CardTakenException the card taken exception
     */
    @Throws(CardTakenException::class)
    fun pickRandomCard(): Card? {
        var c: Card? = null
        do {
            val cardList: List<Card?> = ArrayList(cardIntegerMap.keys)
            val random = cardList[Random().nextInt(cardIntegerMap.size)]
            if (!isCardPicked(random)) c = random
        } while (Objects.isNull(c))
        return pickCard(c)
    }
    /**
     * Calling the init function with i occurrences
     *
     * @param i the number of occurrences of each card
     */
    /**
     * Calling the init function with a single occurrence
     */
    init {
        init(i)
    }
}