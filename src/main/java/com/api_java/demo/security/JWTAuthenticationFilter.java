package com.api_java.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.imageio.IIOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class JWTAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
    //intento de autentificacion

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getUsername(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );
        return getAuthenticationManager().authenticate(usernamePAT);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();  //capturo el objeto de getPrincipal()
        String token = TokenUtils.createToken(userDetails.getUsername()); //creo el token
        response.addHeader("Authorization", "Bearer " + token); //lo agrego al response con el prefijo Bearer
        response.getWriter().flush();
        response.setContentType("application/json;chatset+UFT-8");
        response.getWriter().append("{\'message\': \'Te has logeado correctamente\'}");

        super.successfulAuthentication(request, response, chain, authResult);

    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().append(json());
        }

        private String json(){
            long date = new Date().getTime();
            return "{'status':401," + "'error':'No autorizado'." +
                    "'message': 'Username o password invalido'," + "'path': 'login'}";
        }
    }

}
