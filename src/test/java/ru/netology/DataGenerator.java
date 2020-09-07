package ru.netology;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    private static Faker faker = new Faker(new Locale("ru"));

    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static Client generateClient() {
            return new Client(generateLogin(), generatePassword());

        }

        public static String generateLogin() {
            String[] login =  new String[]{"ИвановИван", "ПетровВасилий", "СветиковаНадежда", "ЛукъяновАртем"};
            return login[itemIndex];
        }

        public static int generatePassword(){
            int[] password = new int[]{};

        }





    }


}
