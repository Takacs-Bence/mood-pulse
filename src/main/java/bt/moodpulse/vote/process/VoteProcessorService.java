package bt.moodpulse.vote.process;

import bt.moodpulse.user.User;
import bt.moodpulse.vote.Vote;
import bt.moodpulse.vote.result.VoteResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class VoteProcessorService {

    private final VoteQueueService voteQueueService;
    private final VoteResult voteResult;
    private final AtomicBoolean processing;

    public VoteProcessorService(VoteQueueService voteQueueService, VoteResult voteResult) {
        this.voteQueueService = voteQueueService;
        this.voteResult = voteResult;
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
                User voter = vote.getVoter();
                // TODO validate and invalid put to invalid basket
                voteResult.countVote(vote.getVoter().getCommunity(), vote.isMood());
            }
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // ignore for now
        }
    }
}
