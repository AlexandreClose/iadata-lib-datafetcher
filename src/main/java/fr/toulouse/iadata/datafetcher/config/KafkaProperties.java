package fr.toulouse.iadata.datafetcher.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Data
@Accessors( prefix = "_str")
public abstract class KafkaProperties
{
    public String _strHost;
    public String _strPort;
    public String _strTopic;

}