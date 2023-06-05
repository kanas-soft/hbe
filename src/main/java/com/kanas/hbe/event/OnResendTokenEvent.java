package com.kanas.hbe.event;

import com.kanas.hbe.domain.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnResendTokenEvent extends ApplicationEvent {

    private User user;
    private String token;
    private Locale locale;
    private String appUrl;

    public OnResendTokenEvent(User user, String token, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.token = token;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
