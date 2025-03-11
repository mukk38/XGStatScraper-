package tr.com.muskar.playwrightScrapping.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class NewMatchDto {

    private String team1;
    private Double team1Xg;
    private String team2;
    private Double team2Xg;
    private String link;
    private LocalTime time;
    private String odd;
}
