package com.coviam.gateway.config;


import com.coviam.gateway.controller.ApiRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Component
public class SuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private static final Logger LOGGER = Logger.getLogger(SuccessHandler.class.getName());


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

       /* SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest == null) {
            clearAuthenticationAttributes(request);
            return;
        }
        String targetUrlParam = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParam != null && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            return;
        }*/
        LOGGER.info("Logged in: "+authentication.getName());
        clearAuthenticationAttributes(request);
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)authentication.getAuthorities();
        List<String> rolelist = new ArrayList<>();
        for(GrantedAuthority authority: authorities){
            rolelist.add(authority.getAuthority());
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        SuccessDTO successDTO = new SuccessDTO();
        successDTO.setRoles(rolelist);
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(successDTO);
        PrintWriter responsewriter  = response.getWriter();
        responsewriter.write(jsonStr);
        responsewriter.flush();
        responsewriter.close();
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}


