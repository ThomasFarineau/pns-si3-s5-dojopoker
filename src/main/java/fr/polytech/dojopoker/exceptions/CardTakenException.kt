package fr.polytech.dojopoker.exceptions

import fr.polytech.dojopoker.cards.Card

class CardTakenException
    (card: Card) : Exception("La carte $card a déjà été retirée du paquet.")