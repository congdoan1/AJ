package sample.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Suzy
 */
public class MyContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("COUNT", 0);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
