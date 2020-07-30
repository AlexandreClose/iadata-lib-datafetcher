/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datafetcher.services;

import fr.toulouse.iadata.datafetcher.config.ProxyConfig;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.elasticsearch.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author cu33443
 */
@Component
public class ProxyCustomizer implements RestTemplateCustomizer {

    @Autowired
    private ProxyConfig proxyConfig;
    
    @Override
    public void customize(RestTemplate restTemplate) {
 

        Properties systemProperties = System.getProperties();
        
        Map<String,String> proxySettingsMap = new HashMap<String,String>();
        
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        clientBuilder.useSystemProperties();
        try {
        
            if ( System.getProperties().containsKey("http.proxyHost") && System.getProperties().containsKey("http.proxyPort") && System.getProperties().containsKey("http.proxyUser") && System.getProperties().containsKey("http.proxyPassword") ){
                
                HttpHost proxy = new HttpHost(systemProperties.getProperty("http.proxyHost").toString(), Integer.parseInt(systemProperties.getProperty("http.proxyPort").toString()));
//                DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
                clientBuilder.setProxy(proxy);
                credentialsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials(systemProperties.getProperty("http.proxyUser"), systemProperties.getProperty("http.proxyPassword")));
                
            }else if(System.getProperties().containsKey("https.proxyHost") && System.getProperties().containsKey("https.proxyPort") && System.getProperties().containsKey("https.proxyUser") && System.getProperties().containsKey("https.proxyPassword") ){

                HttpHost proxy = new HttpHost(systemProperties.getProperty("https.proxyHost").toString(), Integer.parseInt(systemProperties.getProperty("https.proxyPort").toString()));
//                DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
                clientBuilder.setProxy(proxy);
                credentialsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials(systemProperties.getProperty("https.proxyUser"), systemProperties.getProperty("https.proxyPassword")));
               
            }else if(proxyConfig.getHttpProxyHost() != null && proxyConfig.getHttpProxyPort() != null && proxyConfig.getHttpProxyUser() != null && proxyConfig.getHttpProxyPassword() != null){
                HttpHost proxy = new HttpHost(proxyConfig.getHttpProxyHost(), Integer.parseInt(proxyConfig.getHttpProxyPort() ));
//                DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
                clientBuilder.setProxy(proxy);
                credentialsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials(proxyConfig.getHttpProxyUser(), proxyConfig.getHttpProxyPassword()));
               
            }else if(proxyConfig.getHttpsProxyHost() != null && proxyConfig.getHttpsProxyPort() != null && proxyConfig.getHttpsProxyUser() != null && proxyConfig.getHttpProxyPassword() != null){
                HttpHost proxy = new HttpHost(proxyConfig.getHttpProxyHost(), Integer.parseInt(proxyConfig.getHttpProxyPort() ));
//                DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
                clientBuilder.setProxy(proxy);
                credentialsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials(proxyConfig.getHttpProxyUser(), proxyConfig.getHttpProxyPassword()));
               
            }
        
        }catch (NumberFormatException e){}
        
        HttpHost proxy = new HttpHost("dsiproxymairie.mairie.toulouse.intra", 80);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

//        //Client credentials
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        
//        credentialsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials("xx-adm-iadata", "L@p1nrjk"));
// 

        
        
        clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
        clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());
        // Create AuthCache instance
//        AuthCache authCache = (AuthCache) new BasicAuthCache();
//
//        BasicScheme basicAuth = new BasicScheme();
//        authCache.put(proxy, basicAuth);
//        HttpClientContext context = HttpClientContext.create();
//        context.setCredentialsProvider(credentialsProvider);
//        context.setAuthCache(authCache);
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//          .setRoutePlanner(routePlanner)
//          .setDefaultCredentialsProvider(credentialsProvider)
//          .build();
        
//        CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(proxy).build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        CloseableHttpClient httpClient = clientBuilder.build();
        factory.setHttpClient(httpClient);
        
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

}
