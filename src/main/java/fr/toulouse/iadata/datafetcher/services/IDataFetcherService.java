package fr.toulouse.iadata.datafetcher.services;

import fr.toulouse.iadata.datafetcher.config.ApiFetcherProperties;

import java.util.List;

/**
 * Interface for data fetcher services
 */
public interface IDataFetcherService 
{
    /**
     * Process fetch datas
     */
    String fetch();
    void postProcess(List<String> strData);
    void setApiFetcherProperties ( ApiFetcherProperties properties );
}