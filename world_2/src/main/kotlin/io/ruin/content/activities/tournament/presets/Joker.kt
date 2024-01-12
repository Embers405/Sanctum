package io.ruin.content.activities.tournament.presets

import io.ruin.api.utils.Tuple
import io.ruin.content.activities.tournament.TournamentAttributes
import io.ruin.content.activities.tournament.TournamentPlaylist
import io.ruin.model.inter.journal.presets.Preset
import io.ruin.model.item.Item
import io.ruin.model.item.containers.Equipment
import io.ruin.model.skills.prayer.Prayer
import io.ruin.model.stat.StatType

object Joker : TournamentAttributes {

    override fun playlist(): TournamentPlaylist {
        return TournamentPlaylist.JOKER
    }

    override fun equipmentLoadout(): Array<Tuple<Int, Item>> {
        return arrayOf(
            Tuple(Equipment.SLOT_HAT, Item(11919)),
            Tuple(Equipment.SLOT_CAPE, Item(10446)),
            Tuple(Equipment.SLOT_AMULET, Item(Preset.AMULET_OF_FURY)),
            Tuple(Equipment.SLOT_WEAPON, Item(11706)),
            Tuple(Equipment.SLOT_CHEST, Item(12956)),
            Tuple(Equipment.SLOT_LEGS, Item(12957)),
            Tuple(Equipment.SLOT_HANDS, Item(12958)),
            Tuple(Equipment.SLOT_FEET, Item(12959)),
            Tuple(Equipment.SLOT_RING, Item(20657))
        )
    }

    override fun inventoryLoadout(): Array<Tuple<Int, Item>> {
        var index = 0
        return arrayOf(
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index++, Item(4012)),
            Tuple(index, Item(4012))
        )
    }

    override fun skillLoadout(): Array<Tuple<StatType, Int>> {
        return arrayOf(
            Tuple(StatType.Attack, 99),
            Tuple(StatType.Defence, 99),
            Tuple(StatType.Strength, 99),
            Tuple(StatType.Hitpoints, 99),
            Tuple(StatType.Ranged, 99),
            Tuple(StatType.Prayer, 99),
            Tuple(StatType.Magic, 99)
        )
    }

    override fun restrictedPrayers(): Array<Prayer> {
        return arrayOf(Prayer.PROTECT_ITEM, Prayer.REDEMPTION, Prayer.SMITE, Prayer.PROTECT_FROM_MAGIC, Prayer.PROTECT_FROM_MISSILES, Prayer.PROTECT_FROM_MELEE)
    }
}