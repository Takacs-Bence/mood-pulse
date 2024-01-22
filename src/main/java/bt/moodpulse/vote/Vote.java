package bt.moodpulse.vote;

import bt.moodpulse.user.User;

import java.util.UUID;

public class Vote {

    private UUID id;

    private boolean mood;

    private User voter;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isMood() {
        return mood;
    }

    public void setMood(boolean mood) {
        this.mood = mood;
    }

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", mood=" + mood +
                ", voter=" + voter +
                '}';
    }
}
