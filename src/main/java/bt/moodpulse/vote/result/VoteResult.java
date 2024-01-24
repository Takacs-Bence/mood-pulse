package bt.moodpulse.vote.result;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VoteResult {

    private final Map<String, VoteCount> communityMap = new ConcurrentHashMap<>();

    public void countVote(String community, Boolean voteValue) {
        String key = community.toUpperCase();
        VoteCount voteCount = communityMap.get(key);
        if (voteCount == null) {
            voteCount = new VoteCount();
            if (voteValue) {
                voteCount.setNegativeAnswers(0L);
                voteCount.setPositiveAnswers(1L);
            } else {
                voteCount.setNegativeAnswers(1L);
                voteCount.setPositiveAnswers(0L);
            }
        } else {
            if (voteValue) {
                voteCount.setNegativeAnswers(0L);
                voteCount.setPositiveAnswers(1L);
            } else {
                voteCount.setNegativeAnswers(1L);
            }
        }
        communityMap.put(key, voteCount);
    }
}
