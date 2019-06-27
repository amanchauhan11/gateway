package com.coviam.gateway.config;

import com.coviam.gateway.controller.ApiRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class RestEntryPoint  implements AuthenticationEntryPoint {

    private static final Logger LOGGER = Logger.getLogger(RestEntryPoint.class.getName());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        LOGGER.info("Received cookie: "+request.getHeader("cookie"));
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}