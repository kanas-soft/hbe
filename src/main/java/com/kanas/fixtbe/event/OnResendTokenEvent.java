package com.kanas.fixtbe.event;

import com.kanas.fixtbe.domain.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnResendTokenEvent extends ApplicationEvent {

    private User user;
    private String token;
    private String appUrl;

    public OnResendTokenEvent(User user, String token, String appUrl) {
        super(user);
        this.user = user;
        this.token = token;
        this.appUrl = appUrl;
    }
}