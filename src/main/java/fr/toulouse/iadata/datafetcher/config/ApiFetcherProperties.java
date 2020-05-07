package fr.toulouse.iadata.datafetcher.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "apifetcher")
@Data
@Accessors( prefix = "_str")
public class ApiFetcherProperties
{
    public String _strUrlApi;
    public String _strLoginApi;
    public String _strPasswordApi;
    public String _strSecurityType;
    public String _strPollingDelayCronExpression;
}