package bt.moodpulse.vote.result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/result")
public class ResultController {

    private final ResultPresenterService resultPresenterService;

    public ResultController(ResultPresenterService resultPresenterService) {
        this.resultPresenterService = resultPresenterService;
    }

    @GetMapping("/community/{community}")
    public Result getResultByCommunity(@PathVariable String community) {
        return resultPresenterService.presentResult(community);
    }
}
