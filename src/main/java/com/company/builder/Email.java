package com.company.builder;

public class Email {
    private int id;
    private String to;
    private String from;
    private String subject;
    private String content;


    public Email() {
    }

    public Email(String to,String from, String subject, String content) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.content = content;
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




//    public static EmailBuilder emailBuilder() {
//        return new EmailBuilder();
//    }
//
//    public static class EmailBuilder {
//        private String from;
//        private String to;
//        private String subject;
//        private String content;
//
//        public void setFrom(String from) {
//            this.from = from;
//        }
//
//        public EmailBuilder setTo(String to) {
//            this.to = to;
//            return this;
//        }
//
//        public EmailBuilder setSubject(String subject) {
//            this.subject = subject;
//            return this;
//        }
//
//        public EmailBuilder setContent(String content) {
//            this.content = content;
//            return this;
//        }
//
//        public Email build() {
//            return new Email(to, from, subject, content);
//        }
//    }
}
