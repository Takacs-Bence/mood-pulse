package bt.moodpulse.vote;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LocalVoteRepository implements VoteRepository {

    private final Gson gson = new Gson();

    @Override
    public void saveVote(Vote vote) {
        Path path = Paths.get("src", "main", "resources", "votes.txt");
        try {
            Files.writeString(path, vote + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("vote couldn't be saved. id: " + vote.getId());
        }
    }
}
