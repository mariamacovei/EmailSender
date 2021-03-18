package com.company.model;

import java.io.File;

public class Email {
    private int id;
    private String to;
    private String from;
    private String subject;
    private String content;
    private File file;

    public Email() {
    }

    public Email(String to, String from, String subject, String content, File file) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.content = content;
        this.file = file;
    }

    public Email(int id, String from, String subject, String content) {
        this.id = id;
        this.from = from;
        this.subject = subject;
        this.content = content;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
