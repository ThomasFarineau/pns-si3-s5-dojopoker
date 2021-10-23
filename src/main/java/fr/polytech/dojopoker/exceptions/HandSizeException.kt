package fr.polytech.dojopoker.exceptions

import fr.polytech.dojopoker.GameController

class HandSizeException
    (size: Int, actualSize: Int) : Exception(
    GameController.lang["reader.exception.hand.size"].replace("{size}", "$size").replace("{isize}", "$actualSize")
)

