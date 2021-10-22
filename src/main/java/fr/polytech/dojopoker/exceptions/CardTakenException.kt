package fr.polytech.dojopoker.exceptions

import fr.polytech.dojopoker.GameController
import fr.polytech.dojopoker.cards.Card

class CardTakenException
    (card: Card) : Exception(GameController.lang["reader.exception.card.taken"].replace("{card}", "$card"))