package bt.moodpulse.user;

import java.util.UUID;

public class User {

    private UUID id;
    private String community;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }
}
