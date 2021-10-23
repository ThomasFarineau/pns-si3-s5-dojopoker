package fr.thomasfar.dojopoker.exceptions

import fr.thomasfar.dojopoker.GameController

class CardExistException
    (card: String) : Exception(GameController.lang["reader.exception.card.exist"].replace("{card}", card))