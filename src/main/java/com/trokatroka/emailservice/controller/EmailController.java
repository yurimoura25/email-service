package com.trokatroka.emailservice.controller;

import com.trokatroka.emailservice.controller.dto.EmailDTO;
import com.trokatroka.emailservice.enums.Type;
import com.trokatroka.emailservice.model.Email;
import com.trokatroka.emailservice.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = {"https://trokatroka.com", "https://www.trokatroka.com"})
public class EmailController {

    @Autowired
    EmailService emailService;


    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody @Valid EmailDTO emailDTO) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        if(email.getType().equals(Type.SIGNUP)) {
            try {
                email = emailService.sendEmail(email);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            return new ResponseEntity<>(email, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(email,  HttpStatus.OK);
    }
}
