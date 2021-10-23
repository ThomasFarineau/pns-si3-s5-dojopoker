package fr.thomasfar.dojopoker.exceptions

import fr.thomasfar.dojopoker.GameController

class CardFormatException
    (i: Int) : Exception(GameController.lang["reader.exception.card.format"].replace("{card}", "$i"))