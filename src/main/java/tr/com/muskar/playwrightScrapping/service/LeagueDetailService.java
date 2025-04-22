package tr.com.muskar.playwrightScrapping.service;


import com.microsoft.playwright.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.muskar.playwrightScrapping.model.entity.TeamStats;
import tr.com.muskar.playwrightScrapping.repository.TeamStatsRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LeagueDetailService {

    private final double TIMEOUT_SCRAP=2000;

    private final TeamStatsRepository teamStatsRepository;

    public void pullLeagueDetailService(String url,String leagueName){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate(url);
            Map<String,TeamStats> teamStatsMap = new HashMap<>();
            updateMainInfoAboutTeam(page,leagueName,teamStatsMap);
            Locator xGButton = page.locator("button.mat-button-toggle-button:has-text('xG')");
            xGButton.click();
            page.waitForTimeout(TIMEOUT_SCRAP);
            updateDetailInfoAboutTeam(page,teamStatsMap);

            browser.close();
        }
    }

    private void updateMainInfoAboutTeam(Page page,String leagueName,Map<String, TeamStats> teamStatsMap){
        List<ElementHandle> rows = page.querySelectorAll("table.xgs-standings-table tbody tr");

        for (ElementHandle row : rows) {
            List<ElementHandle> cells = row.querySelectorAll("td");

            if (cells.size() < 7) continue;

            TeamStats teamStats = new TeamStats(cells,leagueName);
            teamStatsMap.put(teamStats.getTeamName(),teamStats);
        }
     //   teamStatsRepository.saveAll(teamStatsList);
    }

    private void updateDetailInfoAboutTeam(Page page, Map<String,TeamStats> teamStatsMap){
        List<ElementHandle> rows = page.querySelectorAll("table.xgs-standings-table tbody tr");
        for (ElementHandle row : rows) {
            List<ElementHandle> cells = row.querySelectorAll("td");

            if (cells.size() < 7) continue;

         TeamStats teamStats=   teamStatsMap.get(cells.get(1).innerText().trim());
         if(Objects.nonNull(teamStats)){
             teamStats.setXg(Double.parseDouble(cells.get(3).innerText().trim()) );
             teamStats.setXGC(Double.parseDouble(cells.get(4).innerText().trim()));
         }

        }
       List<TeamStats> teamStatsList =  teamStatsMap.values().stream().toList();
        teamStatsRepository.saveAll(teamStatsList);
    }
}
