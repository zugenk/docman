package com.app.module.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class SimpleCORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
       // System.out.println(">>>>>>>>>> ["+req.getRemoteHost()+":"+req.getRemoteAddr()+":"+req.getRemotePort()+"]");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        
        response.setHeader("access-control-allow-credentials","true");
        //response.setHeader("access-control-allow-origin","chrome-extension://fhbjgbiflinjbdggehcddcbncdddomop");
        response.setHeader("access-control-expose-headers","Access-Control-Allow-Origin,Access-Control-Allow-Credentials");
        
       /* / ======
    	
        Access-Control-Request-Headers: X-MYHEADER
        response.setHeader("Access-Control-Request-Headers: origin, content-type, accept");		
        
        */
        
//        resp.headers().add("Access-Control-Allow-Origin", transport.settings().get("http.cors.allow-origin", HttpHeaders.getHeader(req, "Origin", "*")));
//        resp.headers().add("Access-Control-Allow-Credentials", transport.settings().get("http.cors.allow-credentials", "true"));
        chain.doFilter(req, res);
    }

    public void destroy() {
        // TODO Auto-generated method stub

    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }
}