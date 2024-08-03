package com.javabeanscafe.infrastructure.mail;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.javabeanscafe.application.exception.CustomException;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailBuilder {

    private final static String NOREPLY = "put your mail here";

    @Autowired
    private final Configuration configuration;

    @Autowired
    private final JavaMailSender javaMailSender;

    private String subject;

    private String to;

    @Autowired
    public MailBuilder(Configuration configuration,
            JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    private String template(final String name, final Map<String, Object> params) {
        try {
            return FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(name), params);
        } catch (TemplateException | IOException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    "Erro ao gerar o template", "Bad Request");
        }
    }

    public MailBuilder subject(String parameter) {
        subject = parameter;

        return this;
    }

    public MailBuilder to(String parameter) {
        to = parameter;

        return this;
    }

    public void fire(String path, Map<String, Object> params) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            String template = template(path, params);

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            mimeMessageHelper.setFrom(NOREPLY);

            mimeMessageHelper.setTo(to);

            mimeMessageHelper.setSubject(subject);

            mimeMessageHelper.setText(template, true);

            javaMailSender.send(mimeMessage);
        } catch (CustomException | MessagingException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Erro ao enviar o e-mail " + e.getMessage());
        }
    }

}
