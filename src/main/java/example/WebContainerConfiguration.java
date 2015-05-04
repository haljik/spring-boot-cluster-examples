package example;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebContainerConfiguration {

    @Value("${tomcat.ajp.port}")
    private int ajpPort;

    @Value("${tomcat.ajp.redirect.port}")
    private int ajpRedirectPort;

    @Bean
    public EmbeddedServletContainerFactory containerWithDynamoDBSessionManager() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createAjpConnector());
        return tomcat;
    }

    private Connector createAjpConnector() {
        Connector connector = new Connector("org.apache.coyote.ajp.AjpProtocol");
        connector.setProtocol("AJP/1.3");
        connector.setPort(this.ajpPort);
        connector.setRedirectPort(this.ajpRedirectPort);
        return connector;
    }

}