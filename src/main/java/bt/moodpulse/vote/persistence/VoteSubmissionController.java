package bt.moodpulse.vote.persistence;

import bt.moodpulse.vote.Vote;
import bt.moodpulse.vote.process.VoteQueueService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller to receive votes
 */
@RestController
@RequestMapping("/submissions")
public class VoteSubmissionController {

    private VoteRepository voteRepository;
    private VoteQueueService voteQueueService;

    public VoteSubmissionController(VoteRepository voteRepository, VoteQueueService voteQueueService) {
        this.voteRepository = voteRepository;
        this.voteQueueService = voteQueueService;
    }

    @PostMapping("/vote")
    public void moodVote(@RequestBody Vote vote) {
        voteRepository.saveVote(vote);
        voteQueueService.push(vote);
    }
}
