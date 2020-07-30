/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datafetcher.services;

import org.json.JSONObject;

/**
 *
 * @author cu33443
 */
public interface IKafkaService<T> {
    
    void sendMessageToTopic(T data);
}
