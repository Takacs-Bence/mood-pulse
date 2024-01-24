package bt.moodpulse.vote.result;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ResultPresenterService {

    private final VoteResultService voteResultService;

    public ResultPresenterService(VoteResultService voteResultService) {
        this.voteResultService = voteResultService;
    }

    public Result presentResult(String community) {
        VoteCount voteCount = voteResultService.getVoteCountByCommunity(community);

        if (voteCount == null) {
            return new Result(community, null, null, null, null);
        }

        BigDecimal invalidAnswers = new BigDecimal(voteCount.getInvalidAnswers().get());
        BigDecimal positiveAnswers = new BigDecimal(voteCount.getPositiveAnswers().get());
        BigDecimal negativeAnswers = new BigDecimal(voteCount.getNegativeAnswers().get());

        BigDecimal allAnswers = invalidAnswers.add(positiveAnswers).add(negativeAnswers);

        return new Result(community, allAnswers,
                positiveAnswers.divide(allAnswers, 2, RoundingMode.HALF_UP),
                negativeAnswers.divide(allAnswers, 2, RoundingMode.HALF_UP),
                invalidAnswers.divide(allAnswers, 2, RoundingMode.HALF_UP));
    }
}
