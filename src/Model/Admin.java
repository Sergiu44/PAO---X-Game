package Model;

import java.util.UUID;

public final class Admin extends User {
    private final static Boolean isAdmin = Boolean.TRUE;
    private Boolean isDeleted;

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Admin() {
        super(UUID.randomUUID(), "DEMO", "DEMO", "DEMO-CNP", true);
        this.isDeleted = true;
    }

    public Admin(UUID adminId, String firstName, String lastName, String CNP) {
        super(adminId, firstName, lastName, CNP, true);
        this.isDeleted = true;
    }
}