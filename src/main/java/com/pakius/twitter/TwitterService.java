package com.pakius.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * Created by FBecer01 on 25/11/2015.
 */
@Service
public class TwitterService {

    @Autowired
    private MessageChannel inputToKafka;

    @ServiceActivator
    public void process(Message<?> message)
    {
        //do something else
        inputToKafka.send(
                MessageBuilder.withPayload(message)
                        .setHeader("messageKey", String.valueOf(message.hashCode()))
                        .setHeader("topic", "twitter-topic").build());
    }


}
