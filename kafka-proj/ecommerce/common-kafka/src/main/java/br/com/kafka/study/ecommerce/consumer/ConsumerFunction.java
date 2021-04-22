package br.com.kafka.study.ecommerce.consumer;

import br.com.kafka.study.ecommerce.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;


public interface ConsumerFunction<T> {
    void  consume(ConsumerRecord<String, Message<T>> record) throws Exception;
}
