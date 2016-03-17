package com.pakius.twitter;

import kafka.producer.Partitioner;
import org.springframework.stereotype.Component;

/**
 * Created by FBecer01 on 25/11/2015.
 */
@Component
public class customPartitioner implements Partitioner {
    /**
     * Uses the key to calculate a partition bucket id for routing
     * the data to the appropriate broker partition
     * @return an integer between 0 and numPartitions-1
     */
    @Override
    public int partition(final Object key, final int numPartitions) {
        final String s = (String) key;
        final Integer i = Integer.parseInt(s);
        return i % numPartitions;
    }
}
