package bt.moodpulse.vote.result;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * in-memory result ledger for each community, yet unaware of dates
 */
@Service
public class VoteResultService {

    private final Map<String, VoteCount> communityMap = new ConcurrentHashMap<>();

    /**
     * @param community case-insensitive
     * @param voteValue true for positive, false for negative vote result, for invalid votes leave as {@code null}
     */
    public void countVote(String community, Boolean voteValue) {
        String key = community.toUpperCase();
        VoteCount voteCount = communityMap.get(key);

        if (voteCount == null) {
            voteCount = new VoteCount();
        }

        if (voteValue == null) {
            voteCount.incrementInvalidAnswers();
        } else if (voteValue) {
            voteCount.incrementPositiveAnswers();
        } else {
            voteCount.incrementNegativeAnswers();
        }

        communityMap.put(key, voteCount);
    }

    public VoteCount getVoteCountByCommunity(String community) {
        String key = community.toUpperCase();
        return communityMap.get(key);
    }
}
