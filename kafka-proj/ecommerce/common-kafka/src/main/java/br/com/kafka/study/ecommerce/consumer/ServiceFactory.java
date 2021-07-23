package br.com.kafka.study.ecommerce.consumer;


public interface ServiceFactory<T> {
    ConsumerService<T> create() throws Exception;
}