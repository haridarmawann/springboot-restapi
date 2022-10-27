package hari.darmawan.core.helpers;

import org.springframework.core.env.Environment;

import java.net.UnknownHostException;

public class Utils {
    public String baseUrl(Environment environment) throws UnknownHostException {
//        return "http://"+InetAddress.getLocalHost().getHostAddress()+":"+environment.getProperty("local.server.port");
        return "localhost:8081";
    }
}
