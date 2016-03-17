package com.pakius;

import com.pakius.avro.AvroTweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.integration.kafka.serializer.avro.AvroReflectDatumBackedKafkaEncoder;
import org.springframework.integration.kafka.serializer.avro.AvroSpecificDatumBackedKafkaEncoder;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.util.Properties;


/**
 * Created by fjbecerra on 20/11/15.
 */

@SpringBootApplication
@ImportResource("spring-integration.xml")
public class Application {

    final static Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Autowired
    Environment env;

    @Bean
    public TwitterTemplate twitterTemplate()
    {
        return new TwitterTemplate(
                env.getProperty("twitter.oauth.consumerKey"),
                env.getProperty("twitter.oauth.consumerSecret"),
                env.getProperty("twitter.oauth.accessToken"),
                env.getProperty("twitter.oauth.accessTokenSecret"));
    }


    @Bean
    public PropertiesFactoryBean producerProperties()
    {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        Properties properties = new Properties();
        properties.put("topic.metadata.refresh.interval.ms",3600000);
        properties.put("message.send.max.retries",5);
        properties.put("send.buffer.bytes",5242880);
        propertiesFactoryBean.setProperties(properties);
        return propertiesFactoryBean;
    }


    @Bean
    public AvroReflectDatumBackedKafkaEncoder<String> kafkaReflectionEncoder()
    {
        return new AvroReflectDatumBackedKafkaEncoder<>(String.class);
    }

    @Bean
    public AvroSpecificDatumBackedKafkaEncoder<AvroTweet> kafkaSpecificEncoder()
    {
        return new AvroSpecificDatumBackedKafkaEncoder<>(AvroTweet.class);
    }


}

