package com.pakius.twitter;


import com.pakius.avro.AvroTweet;
import com.pakius.avro.AvroUserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.transformer.Transformer;
import org.springframework.messaging.Message;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

/**
 * Created by fjbecerra on 23/11/15.
 */
@Component
public class TwitterTransform implements Transformer {

    final static Logger logger = LoggerFactory.getLogger(TwitterTransform.class);

    @Override
    public Message<?> transform(Message<?> message) {
            Tweet tweet = (Tweet) message.getPayload();
            AvroTweet avroTweet = new AvroTweet();
            avroTweet.setFromUser(tweet.getFromUser());
            avroTweet.setId(tweet.getId());
            avroTweet.setText(tweet.getText());
            avroTweet.setFromUserId(tweet.getFromUserId());
            avroTweet.setInReplyToScreenName(tweet.getInReplyToScreenName());
            AvroUserProfile avroUserProfile = new AvroUserProfile();
            avroUserProfile.setId(tweet.getUser().getId());
            avroUserProfile.setName(tweet.getUser().getName());
            avroTweet.setUser(avroUserProfile);
            logger.info("user: {}" , avroTweet.getUser().getName());
            return MessageBuilder.withPayload(avroTweet).copyHeaders(message.getHeaders()).build();
    }
}
