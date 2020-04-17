import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.*;

public class Runner {
    public static void main(String[] args) {
        String topicName;

        Scanner in = new Scanner(System.in);
        System.out.println("Please, enter the name of topic");
        topicName = in.nextLine();

        Properties producerProps = new Properties();
        producerProps.put("bootstrap.servers", "localhost:9092");
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer kafkaProducer = new KafkaProducer(producerProps);



        Properties consumerProps = new Properties();
        consumerProps.put("bootstrap.servers", "localhost:9092");
        consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("auto.offset.reset","earliest");
        consumerProps.put("group.id", "1");

        KafkaConsumer kafkaConsumer = new KafkaConsumer(consumerProps);

        kafkaConsumer.subscribe(Collections.singleton(topicName));

        System.out.println("Please, enter your message, or enter \"exit\" for exit");
        boolean itsFirstMessage = true;

        while (!in.hasNext("exit")){
            if (itsFirstMessage == false){
                System.out.println("Please, enter your message, or enter \"exit\" for exit");
            }

            ProducerRecord producerRecord = new ProducerRecord(topicName, "name", in.nextLine());
            kafkaProducer.send(producerRecord);


            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);

            List<String> partitionRecords = new ArrayList<>();
            for (ConsumerRecord<String, String> record : records) {
                partitionRecords.add(record.value());
            }
            itsFirstMessage = false;
            int last_index = partitionRecords.size();
            System.out.println(partitionRecords.get(last_index - 1));

        }
        kafkaProducer.close();
    }
}