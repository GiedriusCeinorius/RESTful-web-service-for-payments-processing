package com.gce.ba.homework.aop;

import com.gce.ba.homework.utils.clientsCountryResolver.ClientsCountryResolver;
import com.gce.ba.homework.utils.clientsCountryResolver.IpStackResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class LoggingHandler {

    private final ClientsCountryResolver clientsCountryResolver;

    public LoggingHandler(ClientsCountryResolver clientsCountryResolver) {
        this.clientsCountryResolver = clientsCountryResolver;
    }

    @Pointcut("within(com.gce.ba.homework.controller.*)")
    public void controller() {
    }

    @Before("controller() && args(..,request)")
    public void logBefore(JoinPoint joinPoint, HttpServletRequest request) {
        String ip = clientsCountryResolver.resolveClientIp(request);
        IpStackResponse ipStackResponse = clientsCountryResolver.resolveClientInfo(ip);
        log.info("Method: " + joinPoint.getSignature().getName() + " User ip address: " + ip + ", country - " + ipStackResponse.getCountry_name() + ", city - " + ipStackResponse.getCity());
    }
}
