package bt.moodpulse.vote.result;

import java.math.BigDecimal;

public class Result {

    private final String community;
    private final BigDecimal allVotes;
    private final BigDecimal positiveVotePercentage;
    private final BigDecimal negativeVotePercentage;
    private final BigDecimal invalidVotePercentage;

    public Result(String community, BigDecimal allVotes, BigDecimal positiveVotePercentage, BigDecimal negativeVotePercentage, BigDecimal invalidVotePercentage) {
        this.community = community;
        this.allVotes = allVotes;
        this.positiveVotePercentage = positiveVotePercentage;
        this.negativeVotePercentage = negativeVotePercentage;
        this.invalidVotePercentage = invalidVotePercentage;
    }

    public String getCommunity() {
        return community;
    }

    public BigDecimal getAllVotePercentage() {
        return allVotes;
    }

    public BigDecimal getPositiveVotePercentage() {
        return positiveVotePercentage;
    }

    public BigDecimal getNegativeVotePercentage() {
        return negativeVotePercentage;
    }

    public BigDecimal getInvalidVotePercentage() {
        return invalidVotePercentage;
    }
}
