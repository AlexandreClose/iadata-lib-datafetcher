package fr.toulouse.iadata.datafetcher.config;

import fr.toulouse.iadata.datafetcher.services.ApiFetcherService;
import fr.toulouse.iadata.datafetcher.services.IDataFetcherService;
import fr.toulouse.iadata.datafetcher.services.IPrepareDatasService;
import fr.toulouse.iadata.datafetcher.services.JsonArraySplitterPrepareDatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataFetcherKawantechConfig
{
    @Autowired
    private ApiFetcherProperties _apiFetcherProperties;

    @Bean
    public IPrepareDatasService getPrepareDatasService( )
    {
        return new JsonArraySplitterPrepareDatasService();
    }

    @Bean
    public IDataFetcherService getDataFetcherService( )
    {
        ApiFetcherService service =  new ApiFetcherService();
        service.setApiFetcherProperties( _apiFetcherProperties );
        return service;
    }
}
