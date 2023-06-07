package com.hackaton.emergenciaEscolar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackaton.emergenciaEscolar.model.Emergency;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmergencyService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailSender;
    @Value("${email.receiver}")
    private String emailReceiver;
    @Value("${twilio.accountSid}")
    private String twilioAccountSid;
    @Value("${twilio.authToken}")
    private String twilioAuthToken;
    private final ObjectMapper objectMapper = new ObjectMapper();
    ;

    @Autowired
    public EmergencyService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendReportByEmailAndWhatsapp(Emergency emergency) {

        Double latitude = emergency.getLatitude();
        Double longitude = emergency.getLongitude();
        String mapsLink = String.format("https://www.google.com/maps?q=" + latitude.toString() + "," + longitude.toString());

        String subRegion = emergency.getSubRegion();
        String region = emergency.getRegion();
        String street = emergency.getStreet();
        String district = emergency.getDistrict();
        String streetNumber = emergency.getStreetNumber();

        String messageToSend = ("Endereço: " + street + ", " + streetNumber + ", " + district + ". " + subRegion + ", " + region + "." + "\n" +
                "Link para localização: " + mapsLink);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailSender);
            helper.setTo(emailReceiver);
            helper.setSubject("EMERGÊNCIA");
            helper.setText(messageToSend);

            mailSender.send(message);

            Twilio.init(twilioAccountSid, twilioAuthToken);
            Message messageWhatsapp = Message.creator(
                    new com.twilio.type.PhoneNumber(System.getenv("NUMBER_RECEIVER")),
                    new com.twilio.type.PhoneNumber(System.getenv("NUMBER_BOT")),
                    messageToSend).create();

            return "Email and Whatsapp emergency sent.";
        } catch (MessagingException e) {
            return "Failed to send emergency.";
        }
    }

    public String transformJsonToEmergency(String json) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode addressNode = rootNode.get("0").get(0);
        Emergency emergency = new Emergency();

        emergency.setLatitude(rootNode.get("latitude").asDouble());
        emergency.setLongitude(rootNode.get("longitude").asDouble());
        emergency.setSubRegion(addressNode.get("subregion").asText());
        emergency.setRegion(addressNode.get("region").asText());
        emergency.setStreet(addressNode.get("street").asText());
        emergency.setDistrict(addressNode.get("district").asText());
        emergency.setStreetNumber(addressNode.get("streetNumber").asText());

        return sendReportByEmailAndWhatsapp(emergency);
    }

}
