package com.xiaofangmoon.web.mvc.user.management;

import org.jolokia.client.*;
import org.jolokia.client.request.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Config {

    public static void main(String[] args) throws Exception {
        J4pClient j4pClient = new J4pClient("http://localhost:8086/jolokia");

        J4pReadRequest req = new J4pReadRequest("com.xiaofangmoon.web.mvc.FrontControllerServlet:type=Superman");

        J4pReadResponse resp = j4pClient.execute(req);
        Map<String, String> vals = resp.getValue();
        Set<String> keySet = vals.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key);
            System.out.println(vals.get(key));
        }
    }
}