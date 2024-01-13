package io.ruin.api.protocol.world;

public enum WorldType {
    //ECO("Sanctum", "https://sanctum.rip/", "73.130.50.93"),

    ECO("Sanctum", "https://174.165.201.232", "174.165.201.232", "174.165.201.232"),
    BETA("Drako BETA", "https://drakops.com", "73.130.50.93", "73.130.50.93"),
    PVP("DrakoPK", "https://drakops.com", "73.130.50.93", "73.130.50.93"),
    DEADMAN("DrakoDMM", "https://drakops.com", "73.130.50.93", "73.130.50.93"),
    DEV("Development", "https://174.165.201.232", "73.130.50.93", "73.130.50.93");

    WorldType(String worldName, String websiteUrl, String gameServerAddress, String fileServerAddress) {
        this.worldName = worldName;
        this.websiteUrl = websiteUrl;
        this.fileServerAddress = fileServerAddress;
        this.gameServerAddress = gameServerAddress;
    }

    public boolean isDeadman() {
        return this == DEADMAN;
    }

    private final String worldName;
    private final String websiteUrl;
    private final String fileServerAddress;

    private final String gameServerAddress;


    public String getWorldName() {
        return worldName;
    }

    public String getWebsiteUrl() { return websiteUrl; }
}