package fr.polytech.dojopoker.exceptions

/**
 * The type Card format not valid exception.
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
class CardFormatException
/**
 * Instantiates a new Card format not valid exception.
 *
 * @param i the unvalid card format id
 */
    (i: Int) : Exception("Le format de la carte $i n'est pas valide.")