package bt.moodpulse.vote.process;

import bt.moodpulse.user.User;
import bt.moodpulse.vote.Vote;
import bt.moodpulse.vote.result.VoteResultService;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class VoteProcessorService {

    private final VoteQueueService voteQueueService;
    private final VoteResultService voteResultService;
    private final AtomicBoolean processing;

    public VoteProcessorService(VoteQueueService voteQueueService, VoteResultService voteResultService) {
        this.voteQueueService = voteQueueService;
        this.voteResultService = voteResultService;
        this.processing = new AtomicBoolean(false);
    }

    public void start() {
        stop();
        this.processing.set(true);
        process();
    }

    public void stop() {
        this.processing.set(false);
    }

    private void process() {
        while (processing.get()) {
            Vote vote = voteQueueService.poll();
            if (vote != null) {
                String community = getCommunity(vote);
                boolean valid = validateVote(vote);

                if (valid) {
                    voteResultService.countVote(community, vote.isMood());
                } else {
                    voteResultService.countVote(community, null);
                }
            }
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // ignore for now
        }
    }

    private boolean validateVote(Vote vote) {
        User voter = vote.getVoter();
        if (voter == null) {
            return false;
        }

        if (voter.getId() == null) {
            return false;
        }

        String community = voter.getCommunity();
        if (community == null || community.length() < 3) {
            return false;
        }

        return vote.getId() != null;
    }

    private String getCommunity(Vote vote) {
        User voter = vote.getVoter();
        if (voter == null || voter.getCommunity() == null || voter.getCommunity().length() < 3) {
            return "n/a";
        }
        return voter.getCommunity();
    }
}
