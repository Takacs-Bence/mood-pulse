package bt.moodpulse.vote.result;

import java.util.concurrent.atomic.AtomicLong;

public class VoteCount {

    private final AtomicLong positiveAnswers = new AtomicLong();
    private final AtomicLong negativeAnswers = new AtomicLong();
    private final AtomicLong invalidAnswers = new AtomicLong();


    public VoteCount() {
        this.positiveAnswers.set(0);
        this.negativeAnswers.set(0);
        this.invalidAnswers.set(0);
    }

    public AtomicLong getPositiveAnswers() {
        return positiveAnswers;
    }

    public AtomicLong getNegativeAnswers() {
        return negativeAnswers;
    }

    public AtomicLong getInvalidAnswers() {
        return invalidAnswers;
    }

    public void incrementPositiveAnswers() {
        this.positiveAnswers.getAndIncrement();
    }

    public void incrementNegativeAnswers() {
        this.negativeAnswers.getAndIncrement();
    }

    public void incrementInvalidAnswers() {
        this.invalidAnswers.getAndIncrement();
    }
}
