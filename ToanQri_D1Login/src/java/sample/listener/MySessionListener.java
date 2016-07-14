package sample.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Suzy
 */
public class MySessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se) {
        Integer count = (Integer) se.getSession().getServletContext().getAttribute("COUNT");
        count++;
        System.out.println("ccc: " + count);
        se.getSession().getServletContext().setAttribute("COUNT", count);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
