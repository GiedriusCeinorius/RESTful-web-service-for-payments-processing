package com.gce.ba.homework.utils.clientsCountryResolver;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


@Component
public class ClientsCountryResolverImpl implements ClientsCountryResolver {

    private RestTemplate restTemplate;

    public ClientsCountryResolverImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String resolveClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr.split(",")[0];
    }

    public IpStackResponse resolveClientInfo(String ip) {
        String resourceUrl = "http://api.ipstack.com/" + ip + "?access_key=bab55a18ab805015d098bd0fb5a0ba54";
        return restTemplate.getForObject(resourceUrl, IpStackResponse.class);
    }
}

