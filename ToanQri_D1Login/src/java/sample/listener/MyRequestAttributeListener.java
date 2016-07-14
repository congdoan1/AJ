package sample.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * Web application lifecycle listener.
 *
 * @author Suzy
 */
public class MyRequestAttributeListener implements ServletRequestAttributeListener {

    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("Add is activated");
        String name = srae.getName();
        String oldValue = srae.getValue().toString();
        String newValue = srae.getServletRequest().getAttribute(name).toString();
        System.out.println("Name: " + name + " -old: " + oldValue + " -new: " + newValue);
    }

    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("Remove is activated");
        String name = srae.getName();
        String oldValue = srae.getValue().toString();
        System.out.println("Name: " + name + " -old: " + oldValue);
    }

    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("Replace is activated");
        String name = srae.getName();
        String oldValue = srae.getValue().toString();
        String newValue = srae.getServletRequest().getAttribute(name).toString();
        System.out.println("Name: " + name + " -old: " + oldValue + " -new: " + newValue);
    }
}
