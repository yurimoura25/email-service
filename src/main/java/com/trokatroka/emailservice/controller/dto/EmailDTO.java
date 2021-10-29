package com.trokatroka.emailservice.controller.dto;

import com.trokatroka.emailservice.enums.Type;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailDTO {

    @NotBlank
    @Email
    private String mailTo;

    @NotBlank
    private String userName;

    private Type type;

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
