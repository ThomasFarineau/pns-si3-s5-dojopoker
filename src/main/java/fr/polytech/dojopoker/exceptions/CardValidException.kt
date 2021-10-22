package fr.polytech.dojopoker.exceptions

class CardValidException
    (card: String) : Exception("La carte $card n'existe pas.")