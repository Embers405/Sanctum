package io.ruin.content.activities.event.impl.eventboss.tabel

import io.ruin.model.item.loot.LootItem
import io.ruin.model.item.loot.LootTable
import io.ruin.utility.Broadcast


/*
 * @project Kronos
 * @author Patrity - https://github.com/Patrity
 * Created on - 7/2/2020
 */
class BrutalLavaDragonLoot : LootTable() {
    init {
        guaranteedItems(
                LootItem(995, 100_000, 250_000, 100), //coins
                LootItem(11943, 1, 1, 100) //coins
        )
        addTable(25,
                LootItem(554, 250, 500, 60),
                LootItem(868, 2, 2, 60),
                LootItem(9193, 40, 70, 50),
                LootItem(560, 60, 120, 50),
                LootItem(9194, 3, 25, 30),
                LootItem(556, 75, 75, 30),
                LootItem(563, 10, 10, 30),
                LootItem(565, 15, 15, 10),
                LootItem(19582, 10, 100, 10)
        )
        addTable(15,
                LootItem(8783, 150, 1250, 70),
                LootItem(2362, 80, 120, 60),
                LootItem(452, 30, 50, 50),
                LootItem(2364, 30, 50, 50),
                LootItem(1514, 25, 60, 50),
                LootItem(1632, 10, 20, 50),
                LootItem(5316, 5, 9, 50),
                LootItem(6571, 1, 2, 50),
                LootItem(3025, 6, 12, 40),
                LootItem(12696, 5, 10, 40),
                LootItem(6686, 5, 12, 40),
                LootItem(392, 150, 250, 40),
                LootItem(1748, 40, 100, 30)
        )
        addTable(10,
                LootItem(1305, 1, 1, 60), //D long
                LootItem(1215, 1, 1, 60), //D Dagger
                LootItem(4585, 1, 1, 60), //D Skirt
                LootItem(4087, 1, 1, 60), //D Legs
                LootItem(1347, 1, 1, 60), //Rune wh
                LootItem(1201, 1, 1, 60), //Rune Kite
                LootItem(1079, 1, 1, 60), //Rune platelegs
                LootItem(1127, 1, 1, 60), //Rune platebody
                LootItem(1163, 1, 1, 60), //Rune full helm
                LootItem(23951, 1, 3, 40) //Enhanced Crystal Key
        )
        addTable(1,
                LootItem(22330, 1, 3, 15).broadcast(Broadcast.GLOBAL), //PVM Box
                LootItem(23053, 1, 1, 10).broadcast(Broadcast.GLOBAL), //Crystal tool seed
                LootItem(4207, 1, 1, 10).broadcast(Broadcast.GLOBAL), //Crystal weapon seed
                LootItem(23971, 1, 1, 3).broadcast(Broadcast.GLOBAL), //Crystal helm
                LootItem(23975, 1, 1, 3).broadcast(Broadcast.GLOBAL), //Crystal body
                LootItem(23979, 1, 1, 3).broadcast(Broadcast.GLOBAL), //Crystal legs
                LootItem(25894, 1, 1, 1).broadcast(Broadcast.GLOBAL), //Bow of faedhinen
                LootItem(23995, 1, 1, 1).broadcast(Broadcast.GLOBAL), //Blade of Saldor
                LootItem(30250, 1, 1, 1).broadcast(Broadcast.GLOBAL)) //$10 bond
    }
}