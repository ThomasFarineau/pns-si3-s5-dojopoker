package fr.polytech.dojopoker

import fr.polytech.dojopoker.cards.Card
import java.util.*

/**
 * The type Hand.
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
internal class Hand(
    val id: Int
) : Comparable<Hand> {


    var cards: MutableList<Card>


    /**
     * this method allows to know if a card has successfully been added the hand
     *
     * @param card the card to add to the hand
     * @return true if the card had been added to the hand, false otherwise
     */
    fun addCard(card: Card): Boolean {
        if (cards.size < 5) {
            cards.sortWith(Comparator.reverseOrder())
            cards.add(card)
            return true
        }
        return false
    }

    /**
     * This method compares the cards one by one in the established order to compare for pairs, threes and fourths.
     *
     * @param hand2 the cards hand to compare
     * @return the highest hand or null in case of a tie
     */
    fun cardsValueCompare(hand2: Hand): Hand? {
        for (i in cards.indices) {
            val compare = cards[i].compareTo(hand2.cards[i])
            if (compare > 0) return this else if (compare < 0) return hand2
        }
        return null
    }

    /**
     * Gets ranking.
     *
     * @return the ranking
     */
    val ranking: HandRankings
        get() = HandComparator(this).ranking

    /**
     * this function allows knowing if the hand is full
     *
     * @return true if the hand contains 5 cards
     */
    val isValid: Boolean
        get() = cards.size == 5


    /**
     * Gets card.
     *
     * @param i the
     * @return the card
     */
    fun getCard(i: Int): Card {
        return cards[i]
    }

    override fun compareTo(h2: Hand): Int {
        if (ranking !== h2.ranking) return ranking.strength - h2.ranking.strength
        val h = cardsValueCompare(h2)
        if (Objects.nonNull(h)) {
            if (h == this) return 1
            if (h == h2) return -1
        }
        return 0
    }

    init {
        this.cards = ArrayList()
    }
}