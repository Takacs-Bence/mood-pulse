package bt.moodpulse;

import bt.moodpulse.vote.LocalVoteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public LocalVoteRepository voteRepository() {
        return new LocalVoteRepository();
    }
}
