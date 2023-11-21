@echo off
set SPRING_DATASOURCE_URL=jdbc:h2:mem:babycare
set SPRING_DATASOURCE_USERNAME=sa
set SPRING_DATASOURCE_PASSWORD=
set SPRING_DATASOURCE_DB_DEV_DRIVER=org.h2.Driver
set SPRING_MAIL_USERNAME=project.smashcode@gmail.com
set SPRING_MAIL_PASSWORD=gudrxzryvohhtorp
mvnw.cmd clean package