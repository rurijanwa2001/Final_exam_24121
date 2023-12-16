package bah_final_24108.bah_final_24108.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;
    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Override
    public void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        try{
            helper.setFrom("aishaabah22@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(message);
        } catch (MessagingException ex) {
            throw new RuntimeException("Failed to send email" ,ex);
        }
    }
}
