package top.andyron.learnjava.profileconditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.ZoneId;

@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    @Profile("!test")
    ZoneId createZoneId() {
        return ZoneId.systemDefault();
    }

    @Bean
    @Profile("test")
    ZoneId createZoneIdForTest() {
        return ZoneId.of("America/New_York");
    }

    @Bean
    @Profile({ "test", "master" }) // 满足test或master
    ZoneId createZoneId2() {
        return ZoneId.systemDefault();
    }

}
