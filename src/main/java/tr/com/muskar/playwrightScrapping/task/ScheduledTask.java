package tr.com.muskar.playwrightScrapping.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tr.com.muskar.playwrightScrapping.service.LeagueDetailService;
import tr.com.muskar.playwrightScrapping.service.XgScoreLeagueService;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTask {

    @Value("${xgscore.web.page}")
    private String webPageUrl;
    @Value("${england.premier.league.web.page}")
    private String englandPremierLeagueWebPage;
    @Value("${laliga.web.page}")
    private String laLigaWebPage;

    private final LeagueDetailService leagueDetailService;
    private final XgScoreLeagueService xgScoreLeagueService;

    @Scheduled(cron = "0 0 8 * * *")
    public void checkActiveMatches() {
        xgScoreLeagueService.checkActiveMatches(webPageUrl);
    }

    @Scheduled(initialDelay = 0, fixedRate = 30, timeUnit = TimeUnit.MINUTES)
    public void pullLeagueStatistics() {
        leagueDetailService.pullLeagueDetailService(englandPremierLeagueWebPage, "Premier League");
        leagueDetailService.pullLeagueDetailService(laLigaWebPage, "La Liga");
    }
}
