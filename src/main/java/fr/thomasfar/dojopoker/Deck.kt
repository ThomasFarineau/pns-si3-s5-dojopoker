package fr.thomasfar.dojopoker

import fr.thomasfar.dojopoker.cards.Card
import fr.thomasfar.dojopoker.cards.CardColor
import fr.thomasfar.dojopoker.exceptions.CardTakenException
import java.util.*

class Deck @JvmOverloads constructor(i: Int = 1) {
    private val cards: MutableMap<Card, Int> = LinkedHashMap()
    private var size = 0

    private fun init(i: Int) {
        size = i
        cards.clear()
        for (value in CardColor.values()) for (j in 2..14) cards[Card(j, value)] = i
    }

    @Throws(CardTakenException::class)
    fun pickCard(c: Card?): Card {
        val card = getCard(c)
        if (!isCardPicked(c)) {
            cards.replace(card, cards[card]!! - 1)
            return card
        }
        throw CardTakenException(card)
    }

    private fun isCardPicked(c: Card?): Boolean {
        return cards[getCard(c)] == 0
    }

    private fun getCard(c: Card?): Card {
        if (Objects.nonNull(c)) for ((key) in cards) if (key.value == c!!.value && key.color == c.color) return key
        throw IllegalArgumentException(GameController.lang["reader.exception.deck.illegal"].replace("{card}", "$c"))
    }

    fun addCard(c: Card) {
        if (isCardPicked(c)) cards.replace(c, cards[c]!! + 1)
    }

    @Throws(CardTakenException::class)
    fun pickRandomCard(): Card {
        var c: Card? = null
        do {
            val cardList: List<Card?> = ArrayList(cards.keys)
            val random = cardList[Random().nextInt(cards.size)]
            if (!isCardPicked(random)) c = random
        } while (Objects.isNull(c))
        return pickCard(c)
    }

    init {
        init(i)
    }
}