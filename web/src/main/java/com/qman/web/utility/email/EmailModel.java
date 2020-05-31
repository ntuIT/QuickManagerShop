package com.qman.web.utility.email;

import java.util.HashMap;
import java.util.Map;

public class EmailModel {

    private String from;

    private String to;

    private String subject;

    private String content;

    private HashMap<String, Object> model;

    public EmailModel() {}

    public EmailModel(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String getFrom() { return from; }

    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }

    public void setTo(String to) { this.to = to; }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return "EmailModel{" + "from='" + from + '\'' + ", to='" + to + '\'' + ", subject='" + subject + '\'' + ", content='" + content + '\'' + '}';
    }

    public void setModel(HashMap<String, Object> model) { this.model = model; }

    public Map<String, Object> getModel() { return model; }
}