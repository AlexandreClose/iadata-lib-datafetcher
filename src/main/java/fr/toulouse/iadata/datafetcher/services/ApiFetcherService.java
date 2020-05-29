package fr.toulouse.iadata.datafetcher.services;

import fr.toulouse.iadata.datafetcher.config.ApiFetcherProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * API fetcher service for fetching datas from configured API
 */
public class ApiFetcherService implements IDataFetcherService
{
    private final static String BASIC = "basic";

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
    @Override
    public String fetchById(String id)
    {
        //Set the url to the api
        ResponseEntity<String> response = getResponseEntityById(id);

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

    public ResponseEntity<String> getResponseEntityById( String id )
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
        ResponseEntity<String> response = restTemplate.getForEntity( _apiFetcherProperties.getUrlApi( )+"/"+id+"/last", String.class);

        return response;
    }



    public void setApiFetcherProperties ( ApiFetcherProperties properties )
    {
        _apiFetcherProperties = properties;
    }


}
