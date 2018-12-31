package com.ramdemo.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

public class AuthMethodNotSupportedException extends AuthenticationServiceException {
	private static final long serialVersionUID = -8017151834269119779L;

	public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }
}
