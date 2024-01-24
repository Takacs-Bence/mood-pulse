package bt.moodpulse.vote.persistence;

import bt.moodpulse.vote.Vote;

/**
 * placeholder for persisting votes
 */
public interface VoteRepository {

    void saveVote(Vote vote);
}
