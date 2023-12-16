package bah_final_24108.bah_final_24108.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body) throws MessagingException;
}
