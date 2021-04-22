package br.com.kafka.study.ecommerce;

import br.com.kafka.study.ecommerce.consumer.ConsumerService;
import br.com.kafka.study.ecommerce.consumer.ServiceRunner;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.concurrent.ExecutionException;

public class EmailService implements ConsumerService<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new ServiceRunner(EmailService::new).start(5);
    }

    public String getConsumerGroup() {
        return EmailService.class.getSimpleName();
    }

    public String getTopic() {
        return "ECOMMERCE_SEND_EMAIL";
    }

    public void parse(ConsumerRecord<String, Message<String>> record) {
        System.out.println("Processing new email");
        System.out.println(record.key());
        System.out.println(record.partition());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Email processed");
    }

}
