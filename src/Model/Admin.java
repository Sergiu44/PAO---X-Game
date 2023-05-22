package Model;

import java.util.UUID;

public final class Admin extends User {
    private UUID adminId;
    private int gamesCreated;
    private int gameVariantsCreated;

    public UUID getAdminId() { return adminId; }
    public void setAdminId(UUID adminId) { this.adminId = adminId; }
    public int getGamesCreated() { return this.gamesCreated; }
    public void setGamesCreated(int gamesCreated) { this.gamesCreated = gamesCreated; }
    public int getGameVariantsCreated() { return gameVariantsCreated; }
    public void setGameVariantsCreated(int gameVariantsCreated) { this.gameVariantsCreated = gameVariantsCreated; }


    public Admin() {
        super(UUID.randomUUID(), "DEMO", "DEMO", "DEMO-CNP");
        this.adminId = UUID.randomUUID();
        this.gamesCreated = 0;
        this.gameVariantsCreated = 0;
    }

    public Admin(UUID adminId, int gamesCreated, int gameVariantsCreated, UUID userId, String firstName, String lastName, String CNP) {
        super(userId, firstName, lastName, CNP);
        this.adminId = adminId;
        this.gamesCreated = gamesCreated;
        this.gameVariantsCreated = gameVariantsCreated;
    }
}