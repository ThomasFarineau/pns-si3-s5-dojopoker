package fr.polytech.dojopoker

import fr.polytech.dojopoker.cards.Card
import fr.polytech.dojopoker.cards.CardColor
import fr.polytech.dojopoker.exceptions.CardTakenException
import java.util.*

internal class Deck @JvmOverloads constructor(i: Int = 1) {
    private val cardIntegerMap: MutableMap<Card?, Int> = LinkedHashMap()
    private var size = 0

    private fun init(i: Int) {
        size = i
        cardIntegerMap.clear()
        for (value in CardColor.values()) for (j in 2..14) cardIntegerMap[Card(j, value)] = i
    }

    @Throws(CardTakenException::class)
    fun pickCard(c: Card?): Card {
        val card = getCard(c)
        if (!isCardPicked(c)) {
            cardIntegerMap.replace(card, cardIntegerMap[card]!! - 1)
            return card
        }
        throw CardTakenException(card)
    }

    private fun isCardPicked(c: Card?): Boolean {
        return cardIntegerMap[getCard(c)] == 0
    }

    private fun getCard(c: Card?): Card {
        if (Objects.nonNull(c)) for ((key) in cardIntegerMap) if (key!!.value == c!!.value && key.color == c.color) return key
        throw IllegalArgumentException(GameController.lang["reader.exception.deck.illegal"].replace("{card}", "$c"))
    }

    fun addCard(c: Card?) {
        if (isCardPicked(c)) cardIntegerMap.replace(c, cardIntegerMap[c]!! + 1)
    }

    @Throws(CardTakenException::class)
    fun pickRandomCard(): Card {
        var c: Card? = null
        do {
            val cardList: List<Card?> = ArrayList(cardIntegerMap.keys)
            val random = cardList[Random().nextInt(cardIntegerMap.size)]
            if (!isCardPicked(random)) c = random
        } while (Objects.isNull(c))
        return pickCard(c)
    }

    init {
        init(i)
    }
}