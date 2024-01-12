package io.ruin.model.map.object.actions.impl.prifddinas.amlodddistrict.gauntlet;

import io.ruin.model.map.Chunk;
//import io.ruin.model.map.object.actions.impl.prifddinas.amlodddistrict.RoomType;

public enum GauntletDef {

    START("Starting", RoomType.START, new Chunk[] {new Chunk(238, 709, 1)})//239, 710

    ;

    public String getName() {
        return name;
    }

    public RoomType getType() {
        return type;
    }


    private Chunk[] baseChunks;

    private String name;
    private RoomType type;

    GauntletDef(String name, RoomType type, Chunk[] baseChunks) {
        this.name = name;
        this.type = type;
        this.baseChunks = baseChunks;
    }

    public Chunk getBaseChunk(int layout) {
        if (layout < 0 || layout >= baseChunks.length)
            throw new IllegalArgumentException();
        return baseChunks[layout];
    }
}
