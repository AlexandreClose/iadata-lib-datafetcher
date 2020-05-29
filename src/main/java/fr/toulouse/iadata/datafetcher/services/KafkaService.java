package fr.toulouse.iadata.datafetcher.services;

import fr.toulouse.iadata.datafetcher.config.KafkaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Kafka service for producing datas to Kafkas topics
 */
@Service
public class KafkaService
{

    @Autowired
    private KafkaTemplate<String, String> _kafkaTemplate;
    @Autowired
    private KafkaProperties _kafkaProperties;

    /**
     * Send strMessage to Kafka configured topic
     * @param strMessage the message to send
     */
    public void sendMessageToParkingTopic( String strMessage )
    {
        _kafkaTemplate.send( _kafkaProperties.getTopicParking( ), strMessage);
    }
    public void sendMessageToPedestrianTopic( String strMessage )
    {
        _kafkaTemplate.send( _kafkaProperties.getTopicPedestrianCount( ), strMessage);
    }


}
