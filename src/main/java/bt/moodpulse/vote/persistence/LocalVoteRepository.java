package bt.moodpulse.vote.persistence;

import bt.moodpulse.vote.Vote;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * appends vote in json format to src/main/resources/votes.txt, which functions as a simple file store <br>
 * it is thread-safe, but should be decoupled with a feeding service for performance if needed
 */
public class LocalVoteRepository implements VoteRepository {

    private final Gson gson = new Gson();
    private final Object lock = new Object();

    @Override
    public void saveVote(Vote vote) {
        String json = gson.toJson(vote, Vote.class);
        Path path = Paths.get("src", "main", "resources", "votes.txt");

        synchronized (lock) {
            try {
                Files.writeString(path, json + System.lineSeparator(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println("vote couldn't be saved. id: " + vote.getId());
            }
        }
    }
}
