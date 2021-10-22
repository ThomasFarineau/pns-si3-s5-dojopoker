package fr.polytech.dojopoker.exceptions

import fr.polytech.dojopoker.GameController

class CardExistException
    (card: String) : Exception(GameController.lang["reader.exception.card.exist"].replace("{card}", card))