package com.pakius;


import kafka.consumer.KafkaStream;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;


import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by fjbecerra on 21/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class TwitterTest {

    @Test
    public void aasdfsd() throws UnsupportedEncodingException {
        List<String> x =  KafkaConsumerHelper.fetchMessage();

        System.out.print("");
    }






}
