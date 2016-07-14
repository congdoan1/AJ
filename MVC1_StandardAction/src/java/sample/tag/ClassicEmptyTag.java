package sample.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 *
 * @author Suzy
 */
public class ClassicEmptyTag implements Tag {
    private PageContext context;
    private Tag parent;

    public void setPageContext(PageContext pc) {
        System.out.println("set Page Context");
        this.context = pc;
    }

    public void setParent(Tag t) {
        System.out.println("set Parent");
        this.parent = t;
    }

    public Tag getParent() {
        System.out.println("get Parent");
        return this.parent;
    }

    public int doStartTag() throws JspException {
        JspWriter out = context.getOut();
        try{
        out.println("Day la Classic Empty Tag<br/>");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        JspWriter out = context.getOut();
        try{
        out.println("Ket thuc Classic Empty Tag<br/>");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public void release() {
        System.out.println("Bye Bye Tag");
    }
    
}
