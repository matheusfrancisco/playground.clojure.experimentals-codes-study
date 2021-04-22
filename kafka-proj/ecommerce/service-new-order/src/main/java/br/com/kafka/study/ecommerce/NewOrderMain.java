package br.com.kafka.study.ecommerce;

import br.com.kafka.study.ecommerce.dispatcher.KafkaDispatcher;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(var orderDispatcher = new KafkaDispatcher<Order>()) {
            try(var emailDispatcher = new KafkaDispatcher<String>()) {
                for (var i = 0; i < 10; i++) {
                    var orderId = UUID.randomUUID().toString();
                    var amount = new BigDecimal(Math.random() * 5000 + 1);
                    var email = Math.random() + "@email.com";
                    var order = new Order(orderId, amount, email);
                    var id = new CorrelationId(NewOrderMain.class.getSimpleName());
                    orderDispatcher.send("ECOMMERCE_NEW_ORDER", email, id, order);
                }
            }
        }

    }


}
