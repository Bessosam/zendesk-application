package TheGang.zendesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zendesk.client.v2.Zendesk;

@Configuration
public class ZendeskConfiguration {

    @Bean
    public Zendesk zendesk() {
        return new Zendesk.Builder("https://wacoco.zendesk.com")
                .setUsername("saaabd010401@student.jenseneducation.se")
                .setToken("8pJu9E6Yp9ceDczyoSdDx9tFus7s4usATbBtQdBd")
                .build();

    }

}