package tr.com.muskar.playwrightScrapping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tr.com.muskar.playwrightScrapping.model.NewMatchDto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ApplicationStartupConfig {

    @Bean
    public Map<String, NewMatchDto> activeMatches(){
        return new ConcurrentHashMap<>();
    }
}
