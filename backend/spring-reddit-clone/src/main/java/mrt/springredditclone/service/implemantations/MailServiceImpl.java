package mrt.springredditclone.service.implemantations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mrt.springredditclone.exceptions.SpringRedditException;
import mrt.springredditclone.model.NotificationEmail;
import mrt.springredditclone.service.interfaces.MailContentBuilder;
import mrt.springredditclone.service.interfaces.MailService;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Override
    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("contact@spring-reddit-clone.com","Spring Reddit Clone");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
//        try{
            mailSender.send(messagePreparator);
            log.info("Activation email sent!");
//        } catch (MailException e) {
//            throw new SpringRedditException("Exception occurred when sending email to " + notificationEmail.getRecipient());
//        }
    }
}
