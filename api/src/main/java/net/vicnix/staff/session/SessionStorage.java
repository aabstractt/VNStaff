package net.vicnix.staff.session;

import java.util.UUID;

public class SessionStorage {

    private final String name;
    private final UUID uuid;

    private Boolean vanished = false;
    private Boolean canSeeStaff = true;

    public SessionStorage(String name, UUID uuid) {
        this.name = name;

        this.uuid = uuid;
    }

    public SessionStorage(String name, UUID uuid, Boolean vanished, Boolean canSeeStaff) {
        this(name, uuid);

        this.vanished = vanished;

        this.canSeeStaff = canSeeStaff;
    }

    public String getName() {
        return this.name;
    }

    public UUID getUniqueId() {
        return this.uuid;
    }

    public Boolean isVanished() {
        return this.vanished;
    }

    public void setVanished(Boolean vanished) {
        this.vanished = vanished;
    }

    public Boolean canSeeStaff() {
        return this.canSeeStaff;
    }

    public void setCanSeeStaff(Boolean canSeeStaff) {
        this.canSeeStaff = canSeeStaff;
    }
}