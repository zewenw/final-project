package com.finalproject.authserver.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@Component
//@WebFilter("/*")
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Add CORS headers to the response
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        // Continue with the filter chain
        chain.doFilter(request, httpResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code (if needed)
    }

    @Override
    public void destroy() {
        // Cleanup code (if needed)
    }
}
