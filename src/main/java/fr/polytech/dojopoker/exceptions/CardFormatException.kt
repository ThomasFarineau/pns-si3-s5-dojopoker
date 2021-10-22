package fr.polytech.dojopoker.exceptions

import fr.polytech.dojopoker.GameController

class CardFormatException
    (i: Int) : Exception(GameController.lang["reader.exception.card.format"].replace("{card}", "$i"))