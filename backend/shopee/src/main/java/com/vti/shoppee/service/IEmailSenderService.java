package com.vti.shoppee.service;

import javax.mail.MessagingException;

public interface IEmailSenderService {
    void sendSimpleMessage(String to, String subject, String text);

    void sendMessageWithHTML(String to, String subject, String text);

}
