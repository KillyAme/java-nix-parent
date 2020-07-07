package com.alevel.jeesandbox;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@WebServlet(name = "ip-list-servlet", urlPatterns = "")
public class IpListServlet extends HttpServlet {

    private final ConcurrentMap<String, String> ipAndUserAgentList = new ConcurrentHashMap<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String remoteHost = req.getRemoteHost();
        String header = req.getHeader("User-Agent");
        PrintWriter writer = resp.getWriter();
        ipAndUserAgentList.remove(remoteHost, header);
        writer.println("<b>" + remoteHost + " : : " + header + "</b>");
        for (Map.Entry<String, String> stringStringEntry : ipAndUserAgentList.entrySet()) {
            writer.println(stringStringEntry.getKey() + " : : " + stringStringEntry.getValue());
        }
        ipAndUserAgentList.put(remoteHost, header);
    }
}
