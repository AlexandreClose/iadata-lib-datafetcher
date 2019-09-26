package fr.toulouse.iadata.apifetcher;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import fr.toulouse.iadata.apifetcher.config.SystemProperties;
import fr.toulouse.iadata.apifetcher.services.IDataFetcherService;
import fr.toulouse.iadata.apifetcher.services.KafkaService;

@EnableScheduling
@SpringBootApplication
public class Application {

	@Autowired
	private IDataFetcherService _dataFetcherService;
	@Autowired
	private KafkaService _kafkaService;
	@Autowired
    private SystemProperties _systemProperties;
	

	public static void main(String[] args) 
	{
		SpringApplication.run( Application.class, args);
	}

	/**
	 * Process initialization
	 */
	@PostConstruct
	public void runAfterInit( )
	{
		Properties props = System.getProperties();
		props.put("http.proxyHost", _systemProperties.getHttpProxyHost( ));
		props.put("http.proxyPort", _systemProperties.getHttpProxyPort( ));
		props.put("https.proxyHost", _systemProperties.getHttpsProxyHost());
		props.put("https.proxyPort", _systemProperties.getHttpsProxyPort());
		props.put("http.proxyUser", _systemProperties.getHttpProxyUser());
		props.put("http.proxyPassword", _systemProperties.getHttpProxyPassword());
	}

	/**
	 * run cron job
	 */
	@Scheduled(cron="${cronJob.cronExpression}")
	private void runCronJob ( )
	{
		//Fetch datas from API
		String strDatas = _dataFetcherService.fetch();

		//Send to kafka
		_kafkaService.sendMessage( strDatas );
	}
}
