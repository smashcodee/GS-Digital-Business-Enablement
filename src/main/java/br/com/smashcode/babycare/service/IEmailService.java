package br.com.smashcode.babycare.service;

public interface IEmailService {
    boolean sendEmail(String to, String title, String content);
}
