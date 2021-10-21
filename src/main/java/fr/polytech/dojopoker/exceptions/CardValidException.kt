package fr.polytech.dojopoker.exceptions

/**
 * The type Card not valid exception.
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
class CardValidException
/**
 * Instantiates a new Card not valid exception.
 *
 * @param card the card string to show
 */
    (card: String) : Exception("La carte $card n'existe pas.")