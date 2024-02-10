package io.ruin.model.activities.wellofgoodwill;

public enum Perks {

    DOUBLE_DROPS("Doubledrops", 35_000_000),
    DOUBLE_SLAYER("Doubleslayer", 15_000_000),
    DOUBLE_WINTERTODT("Doublewinter", 15_000_000),
    DOUBLE_PEST_CONTROL("Doublepest", 10_000_000),
    DOUBLE_XP("Doublexp", 20_000_000),
    //NULL("Null", 0)
    ;


    public int cost;
    public String perkName;

    Perks(String perkname, int cost) {
        this.perkName = perkname;
        this.cost = cost;
    }
}
