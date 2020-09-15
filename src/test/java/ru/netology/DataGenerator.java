package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
        private static Faker faker = new Faker(new Locale("en"));
        private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private DataGenerator() {
    }

    static void setUpAll(Client client) {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(client) // передаём в теле объект, который будет преобразован в JSON
                        .when() // "когда"
                        .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                        .then() // "тогда ожидаем"
                        .statusCode(200);  // код 200 OK
    }

    public static class Registration {
        private Registration() {
        }

        public static Client generateValidClient() {
            Client client = new Client(generateLogin(), generatePassword(), "active");
            setUpAll(client);
            return client;
        }

        public static String generateLogin() {
            return faker.name().username();
        }

        public static String generatePassword(){
            return faker.internet().password();
        }

        public static Client generateBlockedClient(){
            Client client = new Client(generateLogin(), generatePassword(), "blocked");
            setUpAll(client);
            return null;
        }

        public static Client generateInvalidPasswordClient(){
            Client client = new Client(generateLogin(), generatePassword(), "active");
            setUpAll(client);
            return client;
        }



    }


}
