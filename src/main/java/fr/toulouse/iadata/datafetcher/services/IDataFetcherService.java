package fr.toulouse.iadata.datafetcher.services;

/**
 * Interface for data fetcher services
 */
public interface IDataFetcherService 
{
    /**
     * Process fetch datas
     */
    String fetch();
    String fetchById(String id);
}