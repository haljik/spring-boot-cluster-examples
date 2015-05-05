package example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@ConditionalOnProperty(value="session.store.type", havingValue = "redis")
@EnableRedisHttpSession
public class TomcatConfigurationWithRedisSpringSession {
}