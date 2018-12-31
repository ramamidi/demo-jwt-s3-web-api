package com.ramdemo.api.profile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ramdemo.security.auth.JwtAuthenticationToken;
import com.ramdemo.security.model.UserContext;

/**
 * End-point for retrieving logged-in user details.
 */
@RestController
public class ProfileEndpoint {
    @RequestMapping(value="/api/profile/whoami", method=RequestMethod.GET)
    public @ResponseBody UserContext get(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }
}
