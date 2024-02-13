package io.ruin.model.inter.handlers.shopinterface;

import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.item.Item;
import io.ruin.model.shop.Currency;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum CustomShop {
    MELEE_STORE(
            1,
            Currency.COINS,
            new ShopItem[]{
                    new ShopItem(1153,150),
                    new ShopItem(1115,450),
                    new ShopItem(1067,400),
                    new ShopItem(1191,400),
                    new ShopItem(3105,5000),
                    new ShopItem(1163,21200),
                    new ShopItem(1127,50000),
                    new ShopItem(1079,50000),
                    new ShopItem(1201,52000),
                    new ShopItem(4131,20000),

                    new ShopItem(1149,120000),
                    new ShopItem(3140,1500000),
                    new ShopItem(4585,180000),
                    new ShopItem(1187,250000),
                    new ShopItem(11840,180000),
                    new ShopItem(21298,750000),
                    new ShopItem(21301,1000000),
                    new ShopItem(21304,1000000),
                    new ShopItem(6524,300000),
                    new ShopItem(6568,750000),

                    new ShopItem(3751,75000),
                    new ShopItem(3753,75000),
                    new ShopItem(10828,75000),
                    new ShopItem(7458,1000),
                    new ShopItem(7461,60000),
                    new ShopItem(7462,100000),
                    new ShopItem(23389,800000),
                    new ShopItem(23246,3000000),
                    new ShopItem(11283,35000000),
                    new ShopItem(1540,1000),

                    new ShopItem(544,1000),
                    new ShopItem(542,1000),
                    new ShopItem(4502,10000),
                    new ShopItem(7534,5000),
                    new ShopItem(12004,5000000),
                    new ShopItem(4151,1000000),
                    new ShopItem(5698,33000),
                    new ShopItem(1305,90000),
                    new ShopItem(4587,90000),

                    new ShopItem(1323,750),
                    new ShopItem(1327,2000),
                    new ShopItem(1329,2800),
                    new ShopItem(1331,3500),
                    new ShopItem(1333,20000),
                    new ShopItem(1249,60000),
                    new ShopItem(3204,150000),
                    new ShopItem(7158,1500000),
                    new ShopItem(1434,60000),
                    new ShopItem(13271,10000000),

                    new ShopItem(6527,18000),
                    new ShopItem(6528,30000),
                    new ShopItem(6525,15000),
                    new ShopItem(10887,138000),
                    new ShopItem(1319,50000),
                    new ShopItem(20727,84000),
                    new ShopItem(2402,50000),
                    new ShopItem(6746,150000),
                    new ShopItem(4153,30000),

                    new ShopItem(6317,55000),
                    new ShopItem(11037,50000),
                    new ShopItem(11838,7500000),
                    new ShopItem(11804,75000000),
                    new ShopItem(11806,75000000),
                    new ShopItem(11808,75000000),
                    new ShopItem(11824,25000000)


            }
    ),
    RANGED_STORE(
            2,
            Currency.COINS,
            new ShopItem[]{
                    new ShopItem(1169,1000),
                    new ShopItem(1129,1000),
                    new ShopItem(1095, 1000),
                    new ShopItem(1133, 1500),
                    new ShopItem(1097, 1500),
                    new ShopItem(1061,120),
                    new ShopItem(1135,4680),
                    new ShopItem(1099,2340),
                    new ShopItem(1065,2340),
                    new ShopItem(22275, 5000),

                    new ShopItem(10382,40000),
                    new ShopItem(10378, 80000),
                    new ShopItem(10380, 80000),
                    new ShopItem(19927, 40000),
                    new ShopItem(10376, 20000),
                    new ShopItem(23188, 20000),
                    new ShopItem(2499,5616),
                    new ShopItem(2493,2592),
                    new ShopItem(2487,2592),
                    new ShopItem(22278, 5800),

                    new ShopItem(10390,40000),
                    new ShopItem(10386, 80000),
                    new ShopItem(10388, 80000),
                    new ShopItem(19933, 40000),
                    new ShopItem(10384, 20000),
                    new ShopItem(23191, 20000),
                    new ShopItem(2501,6738),
                    new ShopItem(2495,3108),
                    new ShopItem(2489,3108),
                    new ShopItem(22281, 6750),

                    new ShopItem(10374,40000),
                    new ShopItem(10370, 80000),
                    new ShopItem(10372, 80000),
                    new ShopItem(19936, 40000),
                    new ShopItem(10368, 20000),
                    new ShopItem(23194, 20000),
                    new ShopItem(2503,8088),
                    new ShopItem(2497,3732),
                    new ShopItem(2491, 3732),
                    new ShopItem(22284, 9200),

                    new ShopItem(12496,40000),
                    new ShopItem(12492,80000),
                    new ShopItem(12494,80000),
                    new ShopItem(19921,40000),
                    new ShopItem(12490,20000),
                    new ShopItem(23197, 20000),
                    new ShopItem(8880, 1200),
                    new ShopItem(9185, 9720),
                    new ShopItem(21902, 4000000),
                    new ShopItem(21012, 150000000),

                    new ShopItem(10499, 25000),
                    new ShopItem(11926, 8000000),
                    new ShopItem(3749, 75000),
                    new ShopItem(19481, 5000000),
                    new ShopItem(841, 30),
                    new ShopItem(843, 60),
                    new ShopItem(849, 120),
                    new ShopItem(853, 240),
                    new ShopItem(861, 960),
                    new ShopItem(11235, 1000000),

                    new ShopItem(10034, 1225),
                    new ShopItem(11959, 2450),
                    new ShopItem(4212, 200000),
                    new ShopItem(4224, 200000),
                    new ShopItem(884, 12),
                    new ShopItem(890, 48),
                    new ShopItem(892, 240),
                    new ShopItem(21326, 1250),
                    new ShopItem(11212, 4875),
                    new ShopItem(863, 120),

                    new ShopItem(868, 450),
                    new ShopItem(810, 256),
                    new ShopItem(811, 952),
                    new ShopItem(11230, 6000),
                    new ShopItem(826, 120),
                    new ShopItem(830, 752),
                    new ShopItem(19484, 5000),
                    new ShopItem(22804, 8000),
                    new ShopItem(20849, 8000),
                    new ShopItem(8882, 12),

                    new ShopItem(9142, 42),
                    new ShopItem(9143, 150),
                    new ShopItem(9144, 280),
                    new ShopItem(9244, 1000),
                    new ShopItem(9242, 1000),
                    new ShopItem(9243, 1000),
                    new ShopItem(21905, 5000),
                    new ShopItem(21932, 5500),
                    new ShopItem(21944, 6000),
                    new ShopItem(21946, 6500),

                    new ShopItem(21948, 7000),
                    new ShopItem(21950, 8000),
                    new ShopItem(2, 500)
            }
    ),
    MAGIC_STORE(
            3,
            Currency.COINS,
            new ShopItem[]{
                    new ShopItem(556,50),
                    new ShopItem(558, 50),
                    new ShopItem(554, 50),
                    new ShopItem(555, 50),
                    new ShopItem(557, 50),
                    new ShopItem(562, 50),
                    new ShopItem(560, 300),
                    new ShopItem(565,300),
                    new ShopItem(562,212),
                    new ShopItem(566,50),

                    new ShopItem(561,50),
                    new ShopItem(563,50),
                    new ShopItem(9075,50),
                    new ShopItem(21880,650),
                    new ShopItem(4699,50),
                    new ShopItem(4695,50),
                    new ShopItem(4694,50),
                    new ShopItem(4698,50),
                    new ShopItem(4697,50),
                    new ShopItem(4696,50),

                    new ShopItem(579,125),
                    new ShopItem(4089,46000),
                    new ShopItem(4109,46000),
                    new ShopItem(4099,46000),
                    new ShopItem(23047,46000),
                    new ShopItem(13385,8000),
                    new ShopItem(20595,1500000),
                    new ShopItem(22296,20000000),
                    new ShopItem(11908,15000000),
                    new ShopItem(11791,15000000),

                    new ShopItem(577,500),
                    new ShopItem(4091,84000),
                    new ShopItem(4111, 84000),
                    new ShopItem(4101, 84000),
                    new ShopItem(23050, 84000),
                    new ShopItem(13387, 15000),
                    new ShopItem(20517, 3000000),
                    new ShopItem(11924, 8000000),
                    new ShopItem(6914,3000000),
                    new ShopItem(6889,5000000),

                    new ShopItem(1011,325),
                    new ShopItem(4093,84000),
                    new ShopItem(4113,84000),
                    new ShopItem(4103,84000),
                    new ShopItem(23053,84000),
                    new ShopItem(13389,12000),
                    new ShopItem(20520,3000000),
                    new ShopItem(9731,5000),
                    new ShopItem(12829,250000),
                    new ShopItem(12831,500000),

                    new ShopItem(2579,150000),
                    new ShopItem(4097,20000),
                    new ShopItem(4117,20000),
                    new ShopItem(4107,20000),
                    new ShopItem(23059,20000),
                    new ShopItem(6920,1500000),
                    new ShopItem(2417,80000),
                    new ShopItem(2416,80000),
                    new ShopItem(2415,80000),
                    new ShopItem(4170,5000),

                    new ShopItem(1381,2000),
                    new ShopItem(1383,2000),
                    new ShopItem(1387,2000),
                    new ShopItem(3054,18000),
                    new ShopItem(1409,25000),
                    new ShopItem(4675,60000),
                    new ShopItem(20716,7500000),
                    new ShopItem(20718,5000),
                    new ShopItem(25576,7500000),
                    new ShopItem(25578,5000),
                    new ShopItem(20724,35000000)
            }
    ),
    JEWELRY_STORE(
            4,
            Currency.COINS,
            new ShopItem[]{
                    new ShopItem(2550,1200),
                    new ShopItem(1725,7500),
                    new ShopItem(1712,13000),
                    new ShopItem(11972,25000),
                    new ShopItem(11128,150000),
                    new ShopItem(6585,3000000),
                    new ShopItem(12002,7500000),
                    new ShopItem(11980,11000),
                    new ShopItem(11968,11000),
                    new ShopItem(21166,8000),
                    new ShopItem(6737,2000000),
                    new ShopItem(6733,2000000),
                    new ShopItem(6731,2000000),
                    new ShopItem(6735,2000000)
            }
    ),
    UNTRADEABLE_STORE(
            5,
            Currency.COINS,
            new ShopItem[]{
                    new ShopItem(8839,250000),
                    new ShopItem(8840,250000),
                    new ShopItem(11663,250000),
                    new ShopItem(11664,250000),
                    new ShopItem(11665,250000),
                    new ShopItem(8842,50000),
                    new ShopItem(8845,5000),
                    new ShopItem(8846,10000),
                    new ShopItem(8847,20000),
                    new ShopItem(8848,30000),
                    new ShopItem(8849,50000),
                    new ShopItem(8850,100000),
                    new ShopItem(12954,500000),
                    new ShopItem(19722,1200000),
                    new ShopItem(10548,250000),
                    new ShopItem(10550,250000),
                    new ShopItem(2414,25000),
                    new ShopItem(2412,25000),
                    new ShopItem(2413,25000),
                    new ShopItem(12791,500000),
                    new ShopItem(7409,3000000),
                    new ShopItem(10499,10000),
                    new ShopItem(4081,100000),
                    new ShopItem(10588,2500000),
                    new ShopItem(12018,5000000),
                    new ShopItem(11941,100000),
                    new ShopItem(22114,80000),
                    new ShopItem(11200,50000),
                    new ShopItem(11061,120000),
                    new ShopItem(4224,200000),
                    new ShopItem(4212,200000),
                    new ShopItem(19564,50000),
                    new ShopItem(3842,5000),
                    new ShopItem(3844,5000),
                    new ShopItem(12612,5000),
                    new ShopItem(12610,5000),
                    new ShopItem(12608,5000),
                    new ShopItem(3840,5000)
            }
    ),
    PK_POINT_STORE(
            6,
            Currency.SURVIVAL_TOKENS,
            new ShopItem[]{
                    new ShopItem(11802,3000),
                    new ShopItem(11804,750),
                    new ShopItem(11806,750),
                    new ShopItem(11808,750),
                    new ShopItem(24424,10000),
                    new ShopItem(24423,8000),
                    new ShopItem(12002,1200),
                    new ShopItem(19544,3500),
                    new ShopItem(19550,3500),
                    new ShopItem(19553,3500),
                    new ShopItem(19547,3500),
                    new ShopItem(24780,4000),
                    new ShopItem(11828,2000),
                    new ShopItem(11830,2000),
                    new ShopItem(11832,2000),
                    new ShopItem(11834,2000),
                    new ShopItem(13652,6000),
                    new ShopItem(22975,3200),
                    new ShopItem(12929,3000),
                    new ShopItem(12904,3000),
                    new ShopItem(22296,2000),
                    new ShopItem(11924,1200),
                    new ShopItem(11926,1200),
                    new ShopItem(19481,650),
                    new ShopItem(22109,1250),
                    new ShopItem(13124,1000),
                    new ShopItem(21295,10000),
                    new ShopItem(4067,35)
            }
    ),
    CONSUMABLES(
            7,
            Currency.COINS,
            new ShopItem[]{
                    new ShopItem(13441,350),
                    new ShopItem(11936,400),
                    new ShopItem(391,250),
                    new ShopItem(385,500),
                    new ShopItem(7946,250),
                    new ShopItem(373,350),
                    new ShopItem(379,250),
                    new ShopItem(333,75),
                    new ShopItem(3144,600),
                    new ShopItem(2301,375),
                    new ShopItem(7218,325),
                    new ShopItem(4417,600),
                    new ShopItem(2436,1500),
                    new ShopItem(2440,1500),
                    new ShopItem(2442,1500),
                    new ShopItem(2444,4000),
                    new ShopItem(3024,6000),
                    new ShopItem(6685,6000),
                    new ShopItem(3008,850),
                    new ShopItem(2434,4000),
                    new ShopItem(12695,12000),
                    new ShopItem(22461,8000),
                    new ShopItem(22449,8000),
                    new ShopItem(12625,12000),
                    new ShopItem(2452,4000),
                    new ShopItem(12913,6000),
                    new ShopItem(10925,6000)

            }
    ),
    SKILLING_SUPPLIES(
            8,
            Currency.COINS,
            new ShopItem[] {
                    new ShopItem(8013,486),
                    new ShopItem(8015,729),
                    new ShopItem(233, 100),
                    new ShopItem(4155,10),
                    new ShopItem(314,3),
                    new ShopItem(1523,180),
                    new ShopItem(7937,10),
                    new ShopItem(590,90),
                    new ShopItem(2347,72),
                    new ShopItem(952,166),
                    new ShopItem(946,67),
                    new ShopItem(1755,70),
                    new ShopItem(1265,48),
                    new ShopItem(1267,105),
                    new ShopItem(1275,350),
                    new ShopItem(1351,32000),
                    new ShopItem(1349,100),
                    new ShopItem(1359,300),
                    new ShopItem(954,9000),
                    new ShopItem(1778,30),
                    new ShopItem(313,2),
                    new ShopItem(1733,72),
                    new ShopItem(1734,8),
                    new ShopItem(303,50),
                    new ShopItem(305,100),
                    new ShopItem(301,150),
                    new ShopItem(307, 130),
                    new ShopItem(309, 115),
                    new ShopItem(311, 112),
                    new ShopItem(7510, 1250),
                    new ShopItem(1933, 25),
                    new ShopItem(11260, 5),
                    new ShopItem(5509, 1000),
                    new ShopItem(5510, 10000),
                    new ShopItem(5512, 50000),
                    new ShopItem(1785, 100),
                    new ShopItem(1775, 120),
                    new ShopItem(3157, 1000),
                    new ShopItem(3150,150),

                    new ShopItem(10010,150),
                    new ShopItem(11260,150),
                    new ShopItem(10012,150),
                    new ShopItem(10025,150),
                    new ShopItem(10150,150),
                    new ShopItem(10006,150),
                    new ShopItem(10008,150),
                    new ShopItem(10029,150),
                    new ShopItem(596,150),
                    new ShopItem(10031,150),
                    new ShopItem(12740,1500),
                    new ShopItem(12742,1500),
                    new ShopItem(12744,1500),

                    new ShopItem(11940,10)
            }
    ),
    DONATOR_STORE(
            9,
            Currency.OBSIDIAN_COINS,
            new ShopItem[]{
                    new ShopItem(25865,5000),
                    new ShopItem(11802,1500),
                    new ShopItem(26233,3000),
                    new ShopItem(21003,3000),
                    new ShopItem(13239,1500),
                    new ShopItem(13237,1500),
                    new ShopItem(13235,1500),
                    new ShopItem(24271,2500),
                    new ShopItem(21295,3000),
                    new ShopItem(22109,1000),
                    new ShopItem(13652,3000),
                    new ShopItem(13576,4000),
                    new ShopItem(12924,2000),
                    new ShopItem(12904,1500),
                    new ShopItem(11785,2000),
                    new ShopItem(30191,6000),
                    new ShopItem(22981,2500),
                    new ShopItem(22322,3500),
                    new ShopItem(22613, 3000),
                    new ShopItem(24780,1500),
                    new ShopItem(21034,2000),
                    new ShopItem(21079,2000),
                    new ShopItem(21047,2000),
                    new ShopItem(12863,1000),
                    new ShopItem(13173,40000),
                    new ShopItem(13175,30000),
                    new ShopItem(11863,30000),
                    new ShopItem(11862,20000),
                    new ShopItem(11847,20000),
                    new ShopItem(607,500),
                    new ShopItem(6828,1000),
                    new ShopItem(290,1500)

            }
    ),

    VOTE_STORE(
            10,
            Currency.VOTE_MULTIPASS,
            new ShopItem[]{
                    new ShopItem(290, 50),
                    new ShopItem(23951,3),
                    new ShopItem(19564,5),
                    new ShopItem(12791,10),
                    new ShopItem(21295,150),
                    new ShopItem(6758,5),
                    new ShopItem(608,5),
                    new ShopItem(607,5),
                    new ShopItem(21034,50),
                    new ShopItem(21079,50),
                    new ShopItem(786, 1),
                    new ShopItem(10834,200),
                    new ShopItem(13124,25),
                    new ShopItem(12637,25),
                    new ShopItem(12638,25),
                    new ShopItem(12639,25),
                    new ShopItem(962,600),
                    new ShopItem(20017,300),
                    new ShopItem(20005,300),
                    new ShopItem(20546,1),
                    new ShopItem(20545,2),
                    new ShopItem(20544,3),
                    new ShopItem(20543,5),
                    new ShopItem(19836,10),
                    new ShopItem(12804,75),
                    new ShopItem(13256,75),
                    new ShopItem(12771,50),
                    new ShopItem(12769,50),
                    new ShopItem(7671,100),
                    new ShopItem(7673,100)
            }
    ),

    AFK_STORE(
            11,
            Currency.AFK_TOKENS,
            new ShopItem[]{
                    new ShopItem(7409,100000),
                    new ShopItem(11920,1000000),
                    new ShopItem(6739,1000000),
                    new ShopItem(21028,1000000),
                    new ShopItem(12800,200000),
                    new ShopItem(22994,250000),
                    new ShopItem(13226,500000),

                    new ShopItem(10941,100000),
                    new ShopItem(10939,100000),
                    new ShopItem(10940,100000),
                    new ShopItem(10933,100000),

                    new ShopItem(13258,100000),
                    new ShopItem(13259,100000),
                    new ShopItem(13260,100000),
                    new ShopItem(13261,100000),

                    new ShopItem(25592,100000),
                    new ShopItem(25594,100000),
                    new ShopItem(25596,100000),
                    new ShopItem( 25598,100000),

                    new ShopItem(13646,100000),
                    new ShopItem(13642,100000),
                    new ShopItem(13640,100000),
                    new ShopItem(13644,100000),

                    new ShopItem(21343,100000),
                    new ShopItem(21345,200000),
                    new ShopItem(21392,300000),

            }
    ),

    IRONMAN_SHOP(
            12,
            Currency.COINS,
            new ShopItem[] {
                    new ShopItem(314,1),
                    new ShopItem(590,1),
                    new ShopItem(2347,1),
                    new ShopItem(946,1),
                    new ShopItem(1755,1),
                    new ShopItem(1735,1),
                    new ShopItem(1733,1),
                    new ShopItem(1734,3),
                    new ShopItem(1785,5),
                    new ShopItem(1592,10),
                    new ShopItem(1595,10),
                    new ShopItem(1597,10),
                    new ShopItem(11065,10),
                    new ShopItem(5523,175),
                    new ShopItem(1267,245),
                    new ShopItem(1269,2275),
                    new ShopItem(1271,5050),
                    new ShopItem(1275,32000),
                    new ShopItem(1349,98),
                    new ShopItem(1353,325),
                    new ShopItem(1355,900),
                    new ShopItem(1357,2260),
                    new ShopItem(1359,21000),

                    new ShopItem(303,1),
                    new ShopItem(309,1),
                    new ShopItem(301,1),
                    new ShopItem(311,1),

                    new ShopItem(1540,1000),
                    new ShopItem(952,1),
                    new ShopItem(5341,3),
                    new ShopItem(5343,3),
                    new ShopItem(5343,3),
                    new ShopItem(5325,3),
                    new ShopItem(5329,3),

                    new ShopItem(10006,1),
                    new ShopItem(10008,1),
                    new ShopItem(10012,0),
                    new ShopItem(11260,0),
                    new ShopItem(10010,12),
                    new ShopItem(954,10),
                    new ShopItem(227,1),
                    new ShopItem(233,2),
                    new ShopItem(2446,216),
                    new ShopItem(9419,1),
                    new ShopItem(10499,600),
                    new ShopItem(544,24),
                    new ShopItem(542,18),
                    new ShopItem(3105,7),
                    new ShopItem(8880,1200),
                    new ShopItem(8882,1),
                    new ShopItem(2417,48000),
                    new ShopItem(2415,48000),
                    new ShopItem(2416,48000),
                    new ShopItem(9672,4800),
                    new ShopItem(9674,7200),
                    new ShopItem(9676,6000)
            }
    )

    ;


    private ShopItem[] shopItems;
    private Currency currency;
    private int shopId;
    @Getter
    private List<Player> playersInShop;

    CustomShop(final int SHOP_ID, final Currency currency, ShopItem[] shopItems) {
        this.shopId = SHOP_ID;
        this.currency = currency;
        this.shopItems = shopItems;
        playersInShop = new ArrayList<>();
    }

    public static Item[] getItemsFromShop(Player player) {
        int shopId = player.getShopIdentifier();
        if (shopId < 0) {
            player.sendMessage("Something is wrong with this shop. Please contact a staff member.");
            return new Item[0];
        }
        CustomShop shop = Arrays.stream(CustomShop.values())
                .filter(s -> s.shopId == shopId)
                .findFirst()
                .orElse(null);

        if (shop != null) {
            return toItemArray(shop.shopItems);
        }
        return new Item[0];
    }

    public ShopItem[] getShopItems() {
        return shopItems;
    }

    private static Item[] toItemArray(ShopItem[] shopItems) {
        Item[] items = new Item[shopItems.length];
        for (int index = 0; index < shopItems.length; index++) {
            ShopItem shopItem = shopItems[index];
            if (shopItem != null) {
                items[index] = new Item(shopItem.getItemId(), shopItem.getQuantity());
            }
        }
        return items;
    }

    public Item[] getItems() {
        Item[] items = new Item[shopItems.length];
        for (int index = 0; index < shopItems.length; index++) {
            ShopItem shopItem = shopItems[index];
            if (shopItem != null) {
                items[index] = new Item(shopItem.getItemId(), shopItem.getQuantity());
            }
        }
        return items;
    }

    public static CustomShop get(int shopId) {
        return Arrays.stream(CustomShop.values())
                .filter(s -> s.shopId == shopId)
                .findFirst()
                .orElse(null);
    }

    public void addPlayerToShop(Player player) {
        if(!playersInShop.contains(player)) {
            playersInShop.add(player);
        }
    }

    public void removePlayerFromShop(Player player) {
        playersInShop.remove(player);
    }

    public Currency getCurrency() {
        return currency;
    }

    public ShopItem getShopItem(int itemId) {
        return Arrays.stream(shopItems)
                .filter(i -> i.getItemId() == itemId)
                .findFirst()
                .orElse(null);
    }

    public void refreshShop() {
        List<Player> playersToRemove = new ArrayList<>();
        for (Player player : playersInShop) {
            if (player != null) {
                if (!player.hasInterfaceOpen(Interface.CUSTOM_SHOP, InterfaceType.MAIN)) {
                    playersToRemove.add(player);
                    continue;
                }
                player.getPacketSender().sendItems(10005, getItems());
                player.setShopIdentifier(player.getShopIdentifier());
                player.getPacketSender().sendClientScript(917, "ii", -1, -1);
                player.getPacketSender().sendClientScript(10197);
                player.getPacketSender().sendAccessMask(836, 39, 0,
                        127, 1150);
            }
        }
        playersToRemove.stream().filter(Objects::nonNull).forEach(this::removePlayerFromShop);
    }

    public static void openCustomShopViaCmd(Player player) {
        player.getPacketSender().sendString(836, 14, "Melee Store");
        CustomShopInterface.open(player, CustomShop.get(1).getItems());
        CustomShopInterface.handleEnteringShop(player, CustomShop.MELEE_STORE);
        player.setShopIdentifier(1);
    }

    public static void openAFKShop(Player player) {
        player.getPacketSender().sendString(836, 14, "AFK Store");
        CustomShopInterface.open(player, CustomShop.get(11).getItems());
        CustomShopInterface.handleEnteringShop(player, CustomShop.AFK_STORE);
        player.setShopIdentifier(11);
    }

    public static void openIronmanShop(Player player) {
        player.getPacketSender().sendString(836, 14, "Ironman Store");
        CustomShopInterface.open(player, CustomShop.get(12).getItems());
        CustomShopInterface.handleEnteringShop(player, CustomShop.IRONMAN_SHOP);
        player.setShopIdentifier(12);
    }

    static {
        NPCAction.register(3216, "talk-to", (player, npc) -> {
            player.getPacketSender().sendString(836, 14, "Melee Store");
            CustomShopInterface.open(player, CustomShop.get(1).getItems());
            CustomShopInterface.handleEnteringShop(player, CustomShop.MELEE_STORE);
            player.setShopIdentifier(1);
        });
        NPCAction.register(3216, "trade", (player, npc) -> {
            player.getPacketSender().sendString(836, 14, "Melee Store");
            CustomShopInterface.open(player, CustomShop.get(1).getItems());
            CustomShopInterface.handleEnteringShop(player, CustomShop.MELEE_STORE);
            player.setShopIdentifier(1);
        });
        NPCAction.register(7045, 1, (player, npc) -> {
            player.getPacketSender().sendString(836, 14, "Voting Store");
            CustomShopInterface.open(player, CustomShop.get(10).getItems());
            CustomShopInterface.handleEnteringShop(player, CustomShop.VOTE_STORE);
            player.setShopIdentifier(10);
        });
        NPCAction.register(7045, 3, (player, npc) -> {
            player.getPacketSender().sendString(836, 14, "Donator Store");
            CustomShopInterface.open(player, CustomShop.get(9).getItems());
            CustomShopInterface.handleEnteringShop(player, CustomShop.DONATOR_STORE);
            player.setShopIdentifier(9);
        });
    }
}
