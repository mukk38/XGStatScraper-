package tr.com.muskar.playwrightScrapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.muskar.playwrightScrapping.model.entity.TeamStats;

@Repository
public interface TeamStatsRepository extends JpaRepository<TeamStats,String> {
}
