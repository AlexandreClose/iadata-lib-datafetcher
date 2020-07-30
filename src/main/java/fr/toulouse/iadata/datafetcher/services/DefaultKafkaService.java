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
public class DefaultKafkaService implements IKafkaService< String >
{

    @Autowired
    private KafkaTemplate<String, String> _kafkaTemplate;
    @Autowired
    private KafkaProperties _kafkaProperties;

    /**
     * Send strMessage to Kafka configured topic
     * @param strMessage the message to send
     */
    public void sendMessageToTopic( String strMessage )
    {
        _kafkaTemplate.send( _kafkaProperties.getTopic( ), strMessage);
    }



}