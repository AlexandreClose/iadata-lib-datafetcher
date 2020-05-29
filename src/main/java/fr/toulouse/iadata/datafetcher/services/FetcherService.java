package fr.toulouse.iadata.datafetcher.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class FetcherService {
    @Autowired
    private IDataFetcherService _dataFetcherService;
    @Autowired
    private KafkaService _kafkaService;
    @Autowired
    private IPrepareDatasService _prepareDatasService;


    @Scheduled(cron = "${dataFetcher.pollingCronExpression}")
    private void runCronJob() {
        List<String> listDatas = fetch();

        send(listDatas);
    }

    public List<String> fetch() {
        //Fetch datas from API
        String strDatas = _dataFetcherService.fetch();





        List<String> DataList = new ArrayList<>();
        for (String id :  _prepareDatasService.getListId(strDatas)){
            String sensorData = _dataFetcherService.fetchById(id);
            DataList.add(sensorData);
            System.out.println(sensorData);

        }

        return DataList ;


    }

    public void send(List<String> listDatas) {
        //Send to kafka
        for (String strData : listDatas) {
            try {
                JsonNode jsonNode = new ObjectMapper().readTree(strData);

                if (jsonNode.has("parks_array")) {

                    _kafkaService.sendMessageToParkingTopic(strData);

                } else if (jsonNode.has("zones")) {
                    _kafkaService.sendMessageToPedestrianTopic(strData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
