/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datafetcher.services;

import java.net.http.HttpClient;
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
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.elasticsearch.http.HttpException;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author cu33443
 */
public class ProxyCustomizer implements RestTemplateCustomizer {

    @Override
    public void customize(RestTemplate restTemplate) {
        
        
        HttpHost proxy = new HttpHost("dsiproxymairie.mairie.toulouse.intra", 80);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

        //Client credentials
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        
        credentialsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials("cu33443", "Labulldoze-65"));
 



        // Create AuthCache instance
        AuthCache authCache = (AuthCache) new BasicAuthCache();

        BasicScheme basicAuth = new BasicScheme();
        authCache.put(proxy, basicAuth);
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credentialsProvider);
        context.setAuthCache(authCache);

        CloseableHttpClient httpClient = HttpClients.custom()
          .setRoutePlanner(routePlanner)
          .setDefaultCredentialsProvider(credentialsProvider)
          .build();
        
//        CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(proxy).build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

}
