package mrt.springredditclone.service.interfaces;

import mrt.springredditclone.model.NotificationEmail;

public interface MailService {

    void sendMail(NotificationEmail notificationEmail);
}
