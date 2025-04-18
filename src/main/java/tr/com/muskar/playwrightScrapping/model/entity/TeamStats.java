package tr.com.muskar.playwrightScrapping.model.entity;

import com.microsoft.playwright.ElementHandle;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "TEAM_STATS")
@NoArgsConstructor
@Entity
public class TeamStats {
    @Id
    private String teamName;
    private String leagueName;
    private Integer position;
    private Integer matchesPlayed;
    private Integer goalScored;
    private Integer goalConceded;
    private Double xg;
    private Double xGC;
    private Integer points;
    public TeamStats(List<ElementHandle> cells,String leagueName){
        this.leagueName = leagueName;
        this.teamName = cells.get(1).innerText().trim();
        this.position = Integer.parseInt(cells.get(0).innerText().trim());
        this.matchesPlayed = Integer.parseInt(cells.get(2).innerText().trim());
        this.goalScored = Integer.parseInt(cells.get(3).innerText().trim());
        this.goalConceded = Integer.parseInt(cells.get(4).innerText().trim());
        this.points = Integer.parseInt(cells.get(6).innerText().trim());
    }
}
