package com.ramdemo.security.auth.jwt.verifier;

import org.springframework.stereotype.Component;

@Component
public class TBITokenVerifier implements TokenVerifier {
    @Override
    public boolean verify(String jti) {
        return true;
    }
}
