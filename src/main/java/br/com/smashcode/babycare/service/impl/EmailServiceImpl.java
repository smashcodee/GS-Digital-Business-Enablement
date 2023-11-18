package br.com.smashcode.babycare.service.impl;

import br.com.smashcode.babycare.service.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String propertiesMailSender;

    @Override
    public boolean sendEmail(String to, String title, String content) {
        log.info("Preparando para enviar o email...");
        var message = new SimpleMailMessage();
        message.setFrom(propertiesMailSender);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("Email enviado com sucesso para: {}", to);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
