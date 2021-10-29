package com.trokatroka.emailservice.model;


import com.trokatroka.emailservice.enums.Status;
import com.trokatroka.emailservice.enums.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String ownerRef;
    private String mailFrom;
    private String mailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String message;

    private String userName;

    private LocalDateTime date;
    private Status status;
    private Type type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOwnerRef() {
        return ownerRef;
    }

    public void setOwnerRef(String ownerRef) {
        this.ownerRef = ownerRef;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
