package bt.moodpulse.vote.process;

import bt.moodpulse.vote.Vote;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * thread-safe queue to relieve back-pressure <br>
 * only in memory, votes stored here get lost when the server goes down
 */
@Service
public class VoteQueueService {

    private final Queue<Vote> voteQueue = new ConcurrentLinkedQueue<>();

    /**
     * @throws IllegalArgumentException if the {@code vote} is {@code null}
     */
    public void push(Vote vote) {
        if (vote == null) {
            throw new IllegalStateException("null vote cannot be pushed to the queue");
        }
        voteQueue.add(vote);
    }

    /**
     * @return {@code null} if the queue is empty, otherwise the top of the queue
     */
    public Vote poll() {
        if (voteQueue.isEmpty()) {
            return null;
        }
        return voteQueue.poll();
    }
}
