package fr.polytech.dojopoker.exceptions

import fr.polytech.dojopoker.cards.Card

/**
 * The type Card taken exception.
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
class CardTakenException
/**
 * Instantiates a new Card taken exception.
 *
 * @param card the card to show
 */
    (card: Card) : Exception("La carte $card a déjà été retirée du paquet.")