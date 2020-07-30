/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datafetcher.config;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * @author cu33443
 */

@Component
@EnableConfigurationProperties
@Data
public class ProxyConfig {
        
    
    
//    @Value( "${elastic.username}" )
    @Value( "${proxy.http.proxyPort:toto}" )
    private String httpProxyPort;

    @Value( "${proxy.http.proxyHost:toto}" )
    private String httpProxyHost;
    
    @Value( "${proxy.http.proxyUser:toto}" )
    private String httpProxyUser;    
    
    @Value( "${proxy.http.proxyPassword:toto}" )
    private String httpProxyPassword;   
    
    
    @Value( "${proxy.https.proxyPort:toto}" )
    private String httpsProxyPort;

    @Value( "${proxy.https.proxyHost:toto}" )
    private String httpsProxyHost;
    
    @Value( "${proxy.https.proxyUser:toto}" )
    private String httpsProxyUser;    
    
    @Value( "${proxy.https.proxyPassword:toto}" )
    private String httpsProxyPassword;    

    
}