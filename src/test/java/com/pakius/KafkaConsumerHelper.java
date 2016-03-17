package com.pakius;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by FBecer01 on 25/11/2015.
 */

interface KafkaProperties
{
    String zkConnect = "127.0.0.1:2181";
    String groupId = "group1";
    String kafkaServerURL = "localhost";
    int kafkaServerPort = 9092;
    int kafkaProducerBufferSize = 64*1024;
    int connectionTimeOut = 100000;
    int reconnectInterval = 10000;
    String topic = "twitter-topic";
    String clientId = "SimpleConsumerDemoClient";
}


public class KafkaConsumerHelper {

    public static List<String> fetchMessage() throws UnsupportedEncodingException {
        SimpleConsumer simpleConsumer = new SimpleConsumer(KafkaProperties.kafkaServerURL,
                KafkaProperties.kafkaServerPort,
                KafkaProperties.connectionTimeOut,
                KafkaProperties.kafkaProducerBufferSize,
                KafkaProperties.clientId);

        FetchRequest req = new FetchRequestBuilder().clientId(KafkaProperties.clientId)
                .addFetch(KafkaProperties.topic, 0, 0L, 100).build();

        FetchResponse fetchResponse = simpleConsumer.fetch(req);

        ByteBufferMessageSet messageSet = fetchResponse.messageSet(KafkaProperties.topic, 0);
        return printMessages(messageSet);
    }

    private static List<String> printMessages(ByteBufferMessageSet messageSet) throws UnsupportedEncodingException {
        List<String> messages = new LinkedList<>();
        for(MessageAndOffset messageAndOffset: messageSet) {
            ByteBuffer payload = messageAndOffset.message().payload();
            byte[] bytes = new byte[payload.limit()];
            payload.get(bytes);
            messages.add(new String(bytes, "UTF-8"));
         }
        return messages;
    }


}
