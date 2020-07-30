package fr.toulouse.iadata.datafetcher.services;

import fr.toulouse.iadata.datafetcher.config.ApiFetcherProperties;
import fr.toulouse.iadata.datafetcher.config.ProxyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.springframework.boot.web.client.RestTemplateCustomizer;

/**
 * API fetcher service for fetching datas from configured API
 */
public abstract class AbstractApiFetcherService implements IDataFetcherService
{
    private final static String BASIC = "basic";

   @Autowired
   private ApiFetcherProperties _apiFetcherProperties;
   
   @Autowired
   private ProxyConfig proxyConfig;

    @Autowired
    private ProxyCustomizer proxyCustomizer;

    /**
     * {@inheritDoc}
     */
    @Override
    public String fetch() 
    {
        //Set the url to the api
        ResponseEntity<String> response = getResponseEntity();
        
        return response.getBody();
    }



    /**
     * Get the API response entity
     * @return the response entity of the HTTP API call
     */
    public ResponseEntity<String> getResponseEntity( )
    {

        RestTemplate restTemplate = new RestTemplate();

        
        //Add basic auth if API is basic auth secured
        if ( _apiFetcherProperties.getSecurityType( ).equals( BASIC ) )
        {
            proxyCustomizer.customize(restTemplate);
            restTemplate = new RestTemplateBuilder( )
            .basicAuthentication(_apiFetcherProperties.getLoginApi(), _apiFetcherProperties.getPasswordApi() ).customizers(proxyCustomizer)
            .build();
        }
        

        //Set the url to the api
        ResponseEntity<String> response = restTemplate.getForEntity( _apiFetcherProperties.getUrlApi( ), String.class);
        
        return response;
    }



    public void setApiFetcherProperties ( ApiFetcherProperties properties )
    {
        _apiFetcherProperties = properties;
    }


}
