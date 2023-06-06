package com.hackaton.emergenciaEscolar.service;
import com.hackaton.emergenciaEscolar.model.Report;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class ReportService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailSender;
    @Value("${email.receiver}")
    private String emailReceiver;
    @Autowired
    public ReportService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendReportByEmail(Report report) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailSender);
            helper.setTo(emailReceiver);
            helper.setSubject("Denúncia");
            helper.setText(report.getInformation());

            for (MultipartFile file : report.getFiles()) {
                helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
            }

            mailSender.send(message);
            return "Email report sent.";
        } catch (MessagingException e) {
            return "Failed to send report email.";
        }
    }

}
