package com.trokatroka.emailservice.service;

import com.trokatroka.emailservice.enums.Status;
import com.trokatroka.emailservice.model.Email;
import com.trokatroka.emailservice.model.SignUpEmail;
import com.trokatroka.emailservice.repository.EmailRepository;
import com.trokatroka.emailservice.repository.SignUpEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.ValidationException;
import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    SignUpEmailRepository signUpEmailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public Email sendEmail(Email email) {
        email.setDate(LocalDateTime.now());
        if(signUpEmailRepository.findByEmail(email.getMailTo()).isPresent()) {
            throw new ValidationException();
        }
        String htmlTemplate = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                "    <meta name=\"theme-color\" content=\"#000000\" />\n" +
                "    <meta\n" +
                "      name=\"description\"\n" +
                "      content=\"TrokaTroka - Realize trocas de livros\"\n" +
                "    />\n" +
                "    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>\n" +
                "\n" +
                "    <!-- use the font -->\n" +
                "    <style>\n" +
                "      body {\n" +
                "        font-family: 'Roboto', sans-serif;\n" +
                "      }\n" +
                "    </style>\n" +
                "    <title></title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id=\"root\" style=\"background-color: #f8f8f8; width: 100%; height: 100%\">\n" +
                "      <div style=\"margin: 0 0 0 10px;\">\n" +
                "        <h1> Olá, <span style=\"font-weight: bold\">{$nome$}</span></h1>\n" +
                "        <p>{$message$}</p>\n" +
                "        <br><br><br><br>\n" +
                "        <hr>\n" +
                "        <span style=\"text-align: right;color: #ef5d60; font-weight: bold; margin: 0 10px 10px 0\"> Equipe TrokaTroka - <a style=\"color: black; text-decoration: none\" href=\"https://trokatroka.com\"> Acesse nosso site </a></span>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>";
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            email.setSubject("Bem-vindo, solicitação confirmada com sucessoo!");
            email.setMessage("Agradecemos o seu interesse em receber novidades, seu e-mail foi cadastrado com sucesso!");
            helper.setFrom("trokatrokanoreply@gmail.com");
            helper.setTo(email.getMailTo());
            helper.setSubject(email.getSubject());
            helper.setText(htmlTemplate.replace("{$nome$}", email.getUserName()).replace("{$message$}", email.getMessage()), true);

            emailSender.send(mimeMessage);
            email.setStatus(Status.SENT);
        }  catch(MailException | MessagingException ex) {
            email.setStatus(Status.ERROR);
            System.out.println(ex.getMessage());
        }
        switch(email.getType()){
            case SIGNUP:
                SignUpEmail signUpEmail = new SignUpEmail();
                signUpEmail.setEmail(email.getMailTo());
                signUpEmail.setName(email.getUserName());
                signUpEmail.setDataCadastro(LocalDateTime.now());

                signUpEmailRepository.save(signUpEmail);

                break;
        }
        return emailRepository.save(email);
    }
}
