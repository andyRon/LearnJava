package top.andyron.learnjava.customziebean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import top.andyron.learnjava.customziebean.service.MailSession;
import top.andyron.learnjava.customziebean.service.User;
import top.andyron.learnjava.customziebean.service.UserService;

import java.time.ZoneId;

@Configuration
@ComponentScan
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.register("sam@example.com", "password", "Sam");
        User user = userService.login("sam@example.com", "password");
        System.out.println(user.getName());
        context.getBean(MailSession.class);
        context.getBean(MailSession.class);
        context.getBean(MailSession.class);
        ((ConfigurableApplicationContext) context).close();
    }

    @Bean
    @Primary
    @Qualifier("Z")
    ZoneId createZoneOfZ() {
        return ZoneId.of("Z");
    }

    @Bean
    @Qualifier("utc8")
    ZoneId createZoneOfUTC8() {
        return ZoneId.of("UTC+08:00");
    }
}
