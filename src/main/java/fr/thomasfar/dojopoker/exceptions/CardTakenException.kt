package fr.thomasfar.dojopoker.exceptions

import fr.thomasfar.dojopoker.GameController
import fr.thomasfar.dojopoker.cards.Card

class CardTakenException
    (card: Card) : Exception(GameController.lang["reader.exception.card.taken"].replace("{card}", "$card"))