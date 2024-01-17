package raf.sk.sk_user_service.inter_service_comunication;

public class EmailNotification {

    private String emailToNotify;

    private String subject;

    private String message;

    public String getEmailToNotify() {
        return emailToNotify;
    }

    public void setEmailToNotify(String emailToNotify) {
        this.emailToNotify = emailToNotify;
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
}
