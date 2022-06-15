package com.outlierr.blog.infra.principal;

import com.outlierr.blog.api.entity.User;
import com.outlierr.blog.api.enums.AuthType;
import lombok.Value;

import java.security.Principal;

@Value
public class WebPrincipal implements Principal {

    User user;

    public static final WebPrincipal GUEST;

    static {
        User guest = new User();
        guest.setId(0);
        guest.setName("游客");
        guest.setAuth(AuthType.None);
        GUEST = new WebPrincipal(guest);
    }

//    public static final WebPrincipal ANONYMOUS = new WebPrincipal(null);

    @Override
    public String getName() {
        return user.getName();
    }

    public boolean hasPermission() {
        return user != null;
    }
}
