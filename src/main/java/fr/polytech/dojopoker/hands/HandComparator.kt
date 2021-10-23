package fr.polytech.dojopoker.hands

import fr.polytech.dojopoker.cards.Card
import java.util.*

internal class HandComparator(private var cards: MutableList<Card>) {
    val ranking: HandRankings

    private fun repeatOfCards(repeat: Int, number: Int): Boolean {
        val cardsCounter: MutableMap<Int, Int> = HashMap()
        for (card1 in cards) cardsCounter[card1.value] =
            if (cardsCounter.containsKey(card1.value)) cardsCounter[card1.value]!! + 1 else 1
        val counter = cardsCounter.entries.stream().filter { (_, value): Map.Entry<Int, Int> -> value == number }
            .count()
            .toInt()
        return if (counter >= repeat) {
            val cardList2: MutableList<Card> = ArrayList(cards)
            cards.reverse()
            cardList2.reverse()
            val toSort: List<Map.Entry<Int, Int>> = ArrayList<Map.Entry<Int, Int>>(cardsCounter.entries)
            toSort.sortedWith(compareBy { it.value })
            for ((key, value) in toSort) {
                if (value >= number) {
                    cards.stream().filter { card: Card -> card.value == key }.forEach { card: Card? ->
                        cardList2.remove(card)
                        if (card != null) cardList2.add(card)
                    }
                }
            }
            cardList2.reverse()
            cards.clear()
            cards.addAll(cardList2)
            true
        } else false
    }

    private val isFull: Boolean
        get() {
            val cardsCounter: MutableMap<Int, Int> = HashMap()
            for (card in cards) {
                cardsCounter[card.value] =
                    if (cardsCounter.containsKey(card.value)) cardsCounter[card.value]!! + 1 else 1
            }
            if (cardsCounter.size != 2) return false
            val key1 = cardsCounter.keys.toTypedArray()[0]
            val key2 = cardsCounter.keys.toTypedArray()[1]
            return if (cardsCounter[key1] == 3 && cardsCounter[key2] == 2 || cardsCounter[key2] == 3 && cardsCounter[key1] == 2) {
                cards.reverse()
                val cardList2: MutableList<Card> = ArrayList(cards)
                val cardValue = if (cardsCounter[key1] == 3) key1 else key2
                for (card in cards) {
                    if (card.value == cardValue) {
                        cardList2.remove(card)
                        cardList2.add(card)
                    }
                }
                cardList2.reverse()
                cards.clear()
                cards.addAll(cardList2)
                true
            } else false
        }

    private fun init(): HandRankings {
        return when {
            isStraight && isFlush -> HandRankings.STRAIGHT_FLUSH
            repeatOfCards(1, 4) -> HandRankings.FOUR_OF_A_KIND
            isFull -> HandRankings.FULL_HOUSE
            isFlush -> HandRankings.FLUSH
            isStraight -> HandRankings.STRAIGHT
            repeatOfCards(1, 3) -> HandRankings.THREE_OF_A_KIND
            repeatOfCards(2, 2) -> HandRankings.TWO_PAIR
            repeatOfCards(1, 2) -> HandRankings.PAIR
            else -> HandRankings.HIGH_CARD
        }
    }

    private val isStraight: Boolean
        get() {
            cards.sortWith(Collections.reverseOrder())
            for (i in 0 until cards.size - 1) if (cards[i].value - cards[i+1].value != 1) return false
            return true
        }

    private val isFlush: Boolean
        get() {
            for (i in 1 until cards.size) if (cards[i].color != cards[0].color) return false
            return true
        }

    init {
        ranking = init()
    }
}
