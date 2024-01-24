package bt.moodpulse;

import bt.moodpulse.vote.persistence.LocalVoteRepository;
import bt.moodpulse.vote.process.VoteProcessorService;
import bt.moodpulse.vote.process.VoteQueueService;
import bt.moodpulse.vote.result.VoteResultService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public LocalVoteRepository voteRepository() {
        return new LocalVoteRepository();
    }

    @Bean
    public VoteProcessorService voteProcessorService(VoteQueueService voteQueueService, VoteResultService voteResultService) {
        VoteProcessorService voteProcessorService = new VoteProcessorService(voteQueueService, voteResultService);
        voteProcessorService.start();
        return voteProcessorService;
    }
}
