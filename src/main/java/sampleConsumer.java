import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class sampleConsumer {

        public sampleConsumer() {
                Properties consumerProps = new Properties();

                consumerProps.put("bootstrap.servers", "localhost:9092");
                consumerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                consumerProps.put("value.derializer", "org.apache.kafka.common.serialization.StringDerializer");
                consumerProps.put("auto.offset.reset","earliest");

        }


}
