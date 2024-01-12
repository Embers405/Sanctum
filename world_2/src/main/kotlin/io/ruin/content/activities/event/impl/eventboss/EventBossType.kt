package io.ruin.content.activities.event.impl.eventboss

import io.ruin.content.activities.event.impl.eventboss.tabel.BrutalLavaDragonLoot
import io.ruin.content.activities.event.impl.eventboss.tabel.CorruptedNechryarchLoot
import io.ruin.model.item.actions.impl.Pet
import io.ruin.model.item.loot.LootTable
import io.ruin.model.map.Position

enum class EventBossType(val id: Int, val positions: MutableList<Position>, val hitpoints: Int, val message: MutableList<String>, val rolls: Int, val lootTable: LootTable, val embedUrl: String, val pet: Pet, val petChance: Int) {

    WALKER(3358, mutableListOf(
            Position.of(3079, 4013, 0),
            Position.of(3134, 3970, 0),
            Position.of(3225, 3977, 0)), 1200, mutableListOf(
            "Walker has spawned at wilderness level 62!",
            "Walker has spawned at wilderness level 57!",
            "Walker has spawned at wilderness level 58!"), 1, CorruptedNechryarchLoot(),
            "https://drakops.com/Drako/image/Walker.png", Pet.WALKER, 1000),
    HUNLLEF(9021, mutableListOf(
            Position.of(2304, 3560, 0),
            Position.of(3200, 3159, 0),
            Position.of(1530, 3489, 0)), 1600, mutableListOf(
            "Hunllef has spawned south of Piscatoris!",
            "Hunllef has spawned within lumbridge swap!",
            "Hunllef has spawned west of the woodcutting guild!"), 1, BrutalLavaDragonLoot(),
            "https://drakops.com/Drako/image/Hunllef.png", Pet.YOUNGLLEF, 1000)
}
