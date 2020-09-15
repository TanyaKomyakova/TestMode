package ru.netology;

import io.restassured.RestAssured;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private String login;
    private String password;
    private String status;

}
