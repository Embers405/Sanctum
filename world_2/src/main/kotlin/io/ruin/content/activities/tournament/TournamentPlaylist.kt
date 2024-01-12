package io.ruin.content.activities.tournament

import io.ruin.content.activities.tournament.presets.*

/**
 * Different playlist types that a tournament session may use.
 * @author Heaven
 */

enum class TournamentPlaylist(val typeName: String, val attributes: TournamentAttributes) {
    PURE_RANGED_MELEE("Ranged/Melee (1 Def)", PureRangedMeleeTournamentAttributes),
    DHT("Ranged/Melee (1 Def)", dht),
    F2P("Ranged/Melee F2P (40 Def)", f2p),
    JOKER("Funny (Boxing)", Joker),
    NoArm("NoArm", NoArmSetup),
    ZERK("Zerk", Zerk)
    ;

    companion object {
        val VALUES = values()
    }
}