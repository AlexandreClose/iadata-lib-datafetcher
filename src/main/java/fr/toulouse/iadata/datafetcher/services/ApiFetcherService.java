package fr.toulouse.iadata.datafetcher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.toulouse.iadata.datafetcher.config.ApiFetcherProperties;

/**
 * API fetcher service for fetching datas from configured API
 */
@Service
public class ApiFetcherService implements IDataFetcherService
{
    private final static String BASIC = "basic";

    @Autowired
    private ApiFetcherProperties _apiFetcherProperties;

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
            restTemplate = new RestTemplateBuilder( )
            .basicAuthentication( _apiFetcherProperties.getLoginApi(), _apiFetcherProperties.getPasswordApi() )
            .build();
        }
        

        //Set the url to the api
        ResponseEntity<String> response = restTemplate.getForEntity( _apiFetcherProperties.getUrlApi( ), String.class);
        
        return response;
    }

}
