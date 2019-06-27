package com.coviam.gateway.config;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class FailureHandler extends SimpleUrlAuthenticationFailureHandler {

}