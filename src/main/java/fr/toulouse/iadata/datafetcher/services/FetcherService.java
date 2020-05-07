package fr.toulouse.iadata.datafetcher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class FetcherService
{
    @Autowired
    private IDataFetcherService _dataFetcherService;
    @Autowired
    private KafkaService _kafkaService;
    @Autowired
    private IPrepareDatasService _prepareDatasService;


    @Scheduled(cron="${dataFetcher.pollingCronExpression}")
    private void runCronJob ( )
    {
        List<String> listDatas = fetch();
        send( listDatas );
    }

    public List<String> fetch( )
    {
        //Fetch datas from API
        String strDatas = _dataFetcherService.fetch();

        System.out.println( strDatas );

        return _prepareDatasService.prepare( strDatas );


    }

    public void send( List<String> listDatas )
    {
        //Send to kafka

        for ( String strData : listDatas )
        {
            _kafkaService.sendMessage( strData );
        }
    }
}
