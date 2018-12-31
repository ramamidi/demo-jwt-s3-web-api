package com.ramdemo.security.exceptions;

import org.springframework.security.core.AuthenticationException;

import com.ramdemo.security.model.token.JwtToken;

public class JwtExpiredTokenException extends AuthenticationException {
	private static final long serialVersionUID = 7947676144243136694L;

	private JwtToken token;

    public JwtExpiredTokenException(String msg) {
        super(msg);
    }

    public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}
