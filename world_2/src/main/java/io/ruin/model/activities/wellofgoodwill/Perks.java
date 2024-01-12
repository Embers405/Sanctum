package io.ruin.model.activities.wellofgoodwill;

public enum Perks {

    DOUBLE_DROPS("doubledrops", 25_000_000),
    DOUBLE_SLAYER("doubleslayer", 15_000_000),
    DOUBLE_WINTERTODT("doublewinter", 5_000_000),
    DOUBLE_PEST_CONTROL("doublepest", 2_500_000),
    DOUBLE_XP("doublexp", 10_000_000),
    NULL("NULL", 0)
    ;


    public int cost;
    public String perkName;

    Perks(String perkname, int cost) {
        this.perkName = perkname;
        this.cost = cost;
    }
}
