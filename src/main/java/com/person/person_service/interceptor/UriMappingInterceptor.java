package com.person.person_service.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UriMappingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();

        // Mapeia URIs com IDs dinâmicos
        if (uri.matches("/eventos/[a-zA-Z0-9-]+")) {
            uri = "/eventos/{id}";
        }

        request.setAttribute("normalizedUri", uri);
        return true;
    }
}
