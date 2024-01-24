package bt.moodpulse;

import bt.moodpulse.vote.persistence.LocalVoteRepository;
import bt.moodpulse.vote.process.VoteProcessorService;
import bt.moodpulse.vote.process.VoteQueueService;
import bt.moodpulse.vote.result.VoteResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public LocalVoteRepository voteRepository() {
        return new LocalVoteRepository();
    }

    @Bean
    public VoteProcessorService voteProcessorService(VoteQueueService voteQueueService, VoteResult voteResult) {
        VoteProcessorService voteProcessorService = new VoteProcessorService(voteQueueService, voteResult);
        voteProcessorService.start();
        return voteProcessorService;
    }
}
