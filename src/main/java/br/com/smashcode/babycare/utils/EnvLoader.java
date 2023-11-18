package br.com.smashcode.babycare.utils;

import io.github.cdimascio.dotenv.Dotenv;

public abstract class EnvLoader {
    public static void loadEnv() {
        Dotenv env = Dotenv.load();

        /* Datasource Database Dev Load */
        System.setProperty("spring.datasource.url", env.get("SPRING_DATASOURCE_DB_DEV_URL"));
        System.setProperty("spring.datasource.username", env.get("SPRING_DATASOURCE_DB_DEV_USERNAME"));
        System.setProperty("spring.datasource.password", env.get("SPRING_DATASOURCE_DB_DEV_PASSWORD"));
        System.setProperty("spring.datasource.driverClassName", env.get("SPRING_DATASOURCE_DB_DEV_DRIVER"));

        /* Java Mail Sender Load */
        System.setProperty("spring.mail.username", env.get("SPRING_MAIL_USERNAME"));
        System.setProperty("spring.mail.password", env.get("SPRING_MAIL_PASSWORD"));
    }
}
