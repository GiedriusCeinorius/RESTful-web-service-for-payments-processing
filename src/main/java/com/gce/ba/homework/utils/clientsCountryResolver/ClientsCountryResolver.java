package com.gce.ba.homework.utils.clientsCountryResolver;

import javax.servlet.http.HttpServletRequest;

public interface ClientsCountryResolver {


    IpStackResponse resolveClientInfo(String ip);

    String resolveClientIp(HttpServletRequest request);
}
