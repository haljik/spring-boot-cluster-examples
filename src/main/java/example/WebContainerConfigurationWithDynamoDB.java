package example;

import com.amazonaws.services.dynamodb.sessionmanager.DynamoDBSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value="session.store.type", havingValue = "dynamo-db")
public class WebContainerConfigurationWithDynamoDB {

    @Bean
    EmbeddedServletContainerFactory servletConteinerWithDynamoDBSessionManager() {
        final TomcatEmbeddedServletContainerFactory tomcat = tomcat();
        tomcat.addContextCustomizers(context -> {
            context.setManager(dynamoDBSessionManager());
             //stickyなら頻繁に書き込むのはむしろ無駄なのでリスタート時のみで良い。
//            context.setBackgroundProcessorDelay(1);
        });
        return tomcat;
    }

    @Value("${aws.access.key}")
    String awsAccessKey;

    @Value("${aws.secret.key}")
    String awsSecretKey;

    @Value("${aws.dynamodb.region.id}")
    String awsDynamoDBRegionId;

    private DynamoDBSessionManager dynamoDBSessionManager() {
        final DynamoDBSessionManager dynamoDBSessionManager = new DynamoDBSessionManager();
        dynamoDBSessionManager.setAwsAccessKey(awsAccessKey);
        dynamoDBSessionManager.setAwsSecretKey(awsSecretKey);
        dynamoDBSessionManager.setRegionId(awsDynamoDBRegionId);
        dynamoDBSessionManager.setSaveOnRestart(true); //シャットダウン時に永続化する。
        dynamoDBSessionManager.setCreateIfNotExist(true);
        return dynamoDBSessionManager;
    }

    private TomcatEmbeddedServletContainerFactory tomcat() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        return tomcat;
    }

}