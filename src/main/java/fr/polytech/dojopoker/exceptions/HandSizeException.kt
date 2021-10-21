package fr.polytech.dojopoker.exceptions

/**
 * The type Hand size exception.
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
class HandSizeException
/**
 * Instantiates a new Hand size exception.
 *
 * @param size       the size
 * @param actualSize the actual size
 */
    (size: Int, actualSize: Int) :
    Exception("La main doit avoir une taille de " + size + " carte" + (if (actualSize > 1) "s" else "") + ", alors que la main rentrée a une taille de " + actualSize + " carte" + (if (actualSize > 1) "s" else "") + ".")