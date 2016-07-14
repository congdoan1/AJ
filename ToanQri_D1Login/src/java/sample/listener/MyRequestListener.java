package sample.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Web application lifecycle listener.
 *
 * @author Suzy
 */
public class MyRequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent sre) {
    }

    public void requestInitialized(ServletRequestEvent sre) {
        String num = sre.getServletRequest().getParameter("txtInput");
        Boolean result = true;
        try {
            int n = Integer.parseInt(num);
        } catch (NumberFormatException ex) {
            result = false;
        }
        sre.getServletRequest().setAttribute("VALID", result);
    }
}
