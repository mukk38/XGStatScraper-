package tr.com.muskar.playwrightScrapping.service;


import com.microsoft.playwright.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.muskar.playwrightScrapping.model.NewMatchDto;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class XgScoreLeagueService {

    private final Map<String, NewMatchDto> activeMatches;


    public void checkActiveMatches(String url) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate(url);
            List<ElementHandle> fixtures = page.querySelectorAll("xgs-public-forecast-fixture");
            for (ElementHandle fixture : fixtures) {
                try {
                    ElementHandle linkElement = fixture.querySelector("a.xgs-panel_link");
                    String link = (linkElement != null) ? linkElement.getAttribute("href") : "N/A";

                    ElementHandle timeElement = fixture.querySelector("span.xgs-fixture_datetime span.text-muted");
                    String matchTime = (timeElement != null) ? timeElement.innerText().trim() : "Unknown";

                    List<ElementHandle> teams = fixture.querySelectorAll("span.xgs-fixture_team");
                    String team1 = !teams.isEmpty() ? teams.get(0).innerText().trim() : "Unknown";
                    String team2 = teams.size() > 1 ? teams.get(1).innerText().trim() : "Unknown";

                    List<ElementHandle> predictions = fixture.querySelectorAll("mark.xgs-mark strong");
                    String team1_xg = !predictions.isEmpty() ? predictions.get(0).innerText().trim() : "N/A";
                    String team2_xg = predictions.size() > 1 ? predictions.get(1).innerText().trim() : "N/A";

                    ElementHandle oddElement = fixture.querySelector("span.xgs-public-forecast-fixture_odd mark.xgs-mark strong");
                    String odd = (oddElement != null) ? oddElement.innerText().trim() : "N/A";
                    NewMatchDto newMatchDto = new NewMatchDto();
                    newMatchDto.setLink(link);
                    newMatchDto.setTeam1(team1);
                    newMatchDto.setTeam1Xg(Double.parseDouble(team1_xg));
                    newMatchDto.setTeam2(team2);
                    newMatchDto.setTeam2Xg(Double.parseDouble(team2_xg));
                    newMatchDto.setOdd(odd);
                    newMatchDto.setTime(LocalTime.parse(matchTime));
                    activeMatches.put(link, newMatchDto);

                } catch (Exception e) {
                    System.out.println("Error Occured: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            browser.close();
        }
    }
}
