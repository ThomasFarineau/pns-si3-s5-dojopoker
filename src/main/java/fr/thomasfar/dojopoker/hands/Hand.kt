package fr.thomasfar.dojopoker.hands

import fr.thomasfar.dojopoker.cards.Card
import java.util.*

class Hand(val id: Int) : Comparable<Hand> {
    var cards: MutableList<Card> = ArrayList()

    private fun cardsValueCompare(hand2: Hand): Hand? {
        for (i in cards.indices) {
            val compare = cards[i].compareTo(hand2.cards[i])
            if (compare > 0) return this else if (compare < 0) return hand2
        }
        return null
    }

    val ranking: HandRankings
        get() = HandComparator(cards).ranking

    val isValid: Boolean
        get() = cards.size == 5

    override fun compareTo(other: Hand): Int {
        if (ranking !== other.ranking) return ranking.strength - other.ranking.strength
        val h = cardsValueCompare(other)
        if (Objects.nonNull(h)) {
            if (h == this) return 1
            if (h == other) return -1
        }
        return 0
    }

}