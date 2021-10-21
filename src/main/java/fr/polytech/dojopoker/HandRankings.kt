package fr.polytech.dojopoker

/**
 * The enum Hand rankings.
 *
 * @author [Thomas Farineau](https://github.com/ThomasFarineau)
 * @author [Ambre Correia](https://github.com/AmbreCorreia)
 * @author [Quentin Dubois](https://github.com/QuentinDubois-Polytech)
 * @author [Zinedine Chelgham](https://github.com/Chelgham-Zinedine)
 * @version 1.0
 */
internal enum class HandRankings(val strength: Int,val readName: String) {
    STRAIGHTFLUSH(8, "quinte flush"),
    SQUARE(7, "carré"),
    FULL(6, "full"),
    FLUSH(5, "couleur"),
    STRAIGHT(4, "suite"),
    BRELAN(3, "brelan"),
    DOUBLEPAIR(2, "double paire"),
    PAIR(1, "paire"),
    HIGHESTCARD(0, "carte la plus élevée");

    /**
     * Compare two CombinationType with their strength
     *
     * @param handRankings the CombinationType to compare
     * @return the difference of strength
     */
    fun compare(handRankings: HandRankings): Int {
        return strength - handRankings.strength
    }
}