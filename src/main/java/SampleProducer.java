import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SampleProducer {
    public SampleProducer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        ProducerRecord producerRecord = new ProducerRecord("channel", "name", "val");

        KafkaProducer kafkaProducer = new KafkaProducer(properties);

        kafkaProducer.send(producerRecord);
        kafkaProducer.close();

    }
}