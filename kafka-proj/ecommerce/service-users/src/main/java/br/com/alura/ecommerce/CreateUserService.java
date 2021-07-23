package br.com.alura.ecommerce;

import br.com.kafka.study.ecommerce.Message;
import br.com.kafka.study.ecommerce.consumer.ConsumerService;
import br.com.kafka.study.ecommerce.consumer.ServiceRunner;
import br.com.study.kafka.ecommerce.database.LocalDatabase;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class CreateUserService implements ConsumerService<Order> {

    private final LocalDatabase database;

    CreateUserService() throws SQLException {
        this.database = new LocalDatabase("users_database");
        this.database.createIfNotExists("create table Users("+
                "uuid varchar(200) primary key,"+
                "email varchar(200))");
    }

    public static void main(String[] args) throws SQLException, ExecutionException, InterruptedException {
        new ServiceRunner<>(CreateUserService::new).start(1);
    }

    public void parse(ConsumerRecord<String, Message<Order>> record) throws SQLException {
        System.out.println("============================================");
        System.out.println("Processing new order, checking for new user");
        System.out.println(record.value());
        var order = record.value().getPayload();
        if(isNewUser(order.getEmail())) {
            insertNewUser(order.getEmail());
        }
    }

    private boolean isNewUser(String email) throws SQLException {
        var results = database.query("select uuid from Users "
                + "where email = ? limit 1", email);
        return !results.next();
    }

    private void insertNewUser(String email) throws SQLException {
        database.update("insert into Users (uuid, email) " +
                "values (?,?)",
                UUID.randomUUID().toString(),
                email);
        System.out.println("User insert" +  email + "uuid");
    }


    @Override
    public String getTopic() {
        return "ECOMMERCE_NEW_ORDER";
    }

    @Override
    public String getConsumerGroup() {
        return CreateUserService.class.getSimpleName();
    }
}