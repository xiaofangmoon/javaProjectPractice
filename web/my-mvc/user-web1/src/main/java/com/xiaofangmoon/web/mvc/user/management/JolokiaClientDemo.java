package com.xiaofangmoon.web.mvc.user.management;

import org.jolokia.client.J4pClient;
import org.jolokia.client.request.J4pReadRequest;
import org.jolokia.client.request.J4pReadResponse;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaofang
 * @createTime 2021/03/15 00:30
 * @description
 */
public class JolokiaClientDemo {
    public static void main(String[] args) throws Exception {
        String url = "http://localhost:7777/jolokia";
        String objectName = "com.xiaofangmoon.web.mvc.user.management.mxbean.UserManagerX:type=UserManagerX";
        String libraryObjectName = "com.xiaofangmoon.web.mvc.user.management.mxbean.Libraries:type=Libraries";
        printAttributeInfo(url, libraryObjectName);
    }


    static void printAttributeInfo(String url, String objectName) throws Exception {
        J4pClient j4pClient = new J4pClient(url);
        J4pReadRequest req = new J4pReadRequest(objectName);
        J4pReadResponse resp = j4pClient.execute(req);
        Map<String, Object> valueMap = resp.getValue();
        Set<String> keySet = valueMap.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + valueMap.get(key));
        }
    }
}
