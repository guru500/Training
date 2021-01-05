package com.copious.training.security;

import com.copious.training.exceptions.GenericResponse;
import com.copious.training.exceptions.JwtFilterException;
import com.copious.training.exceptions.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FilterExceptionHandler extends OncePerRequestFilter {

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtFilterException ex) {
            Response res = new Response();
            res.setMessage(ex.getErrorMessage());
            res.setStatus(ex.getErrorCode().name());

            GenericResponse<Response> genericResponse = new GenericResponse<>(false,
                    ex.getErrorCode().name(), res);

            response.setHeader("Content-Type", "application/json");
            response.getWriter().write(convertObjectToJson(genericResponse));
            response.setStatus(ex.getErrorCode().value());
        }

    }
}
