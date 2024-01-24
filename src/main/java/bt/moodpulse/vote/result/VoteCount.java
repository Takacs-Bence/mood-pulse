package bt.moodpulse.vote.result;

import java.util.concurrent.atomic.AtomicLong;

public class VoteCount {

    private final AtomicLong positiveAnswers = new AtomicLong();
    private final AtomicLong negativeAnswers = new AtomicLong();

    public AtomicLong getPositiveAnswers() {
        return positiveAnswers;
    }

    public void setPositiveAnswers(Long positiveAnswers) {
        this.positiveAnswers.set(positiveAnswers);
    }

    public AtomicLong getNegativeAnswers() {
        return negativeAnswers;
    }

    public void setNegativeAnswers(Long negativeAnswers) {
        this.negativeAnswers.set(negativeAnswers);
    }

    public void incrementPositiveAnswers() {
        this.positiveAnswers.getAndIncrement();
    }

    public void incrementNegativeAnswers() {
        this.negativeAnswers.getAndIncrement();
    }
}
