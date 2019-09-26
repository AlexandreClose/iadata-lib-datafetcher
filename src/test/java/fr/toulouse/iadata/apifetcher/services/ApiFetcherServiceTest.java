package fr.toulouse.iadata.apifetcher.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

/**
 * API fetcher service for fetching datas from configured API
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiFetcherServiceTest
{

    @Autowired
    private ApiFetcherService _apiFetcherService;

    /**
     * Test the 200 http status code of the parametrized API
     */
    @Test
    public void testGetResponseEntity() 
    {
        ResponseEntity<String> response = _apiFetcherService.getResponseEntity();
        
        assertEquals( response.getStatusCode( ), HttpStatus.OK );
	}

}
