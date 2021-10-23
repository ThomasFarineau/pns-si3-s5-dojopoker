package fr.polytech.dojopoker.hands

import fr.polytech.dojopoker.GameController

enum class HandRankings(val strength: Int, val readName: String) {
    //ROYAL_STRAIGHT_FLUSH(9, GameController.lang["rankings.royal_straight_flush"]),
    STRAIGHT_FLUSH(8, GameController.lang["rankings.straight_flush"]),
    FOUR_OF_A_KIND(7, GameController.lang["rankings.four_of_a_kind"]),
    FULL_HOUSE(6, GameController.lang["rankings.full_house"]),
    FLUSH(5, GameController.lang["rankings.flush"]),
    STRAIGHT(4, GameController.lang["rankings.straight"]),
    THREE_OF_A_KIND(3, GameController.lang["rankings.three_of_a_kind"]),
    TWO_PAIR(2, GameController.lang["rankings.two_pair"]),
    PAIR(1, GameController.lang["rankings.pair"]),
    HIGH_CARD(0, GameController.lang["rankings.high_card"]);

    fun compare(handRankings: HandRankings): Int {
        return strength - handRankings.strength
    }
}