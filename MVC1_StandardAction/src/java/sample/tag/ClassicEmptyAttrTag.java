package sample.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


/**
 *
 * @author Suzy
 */
public class ClassicEmptyAttrTag extends TagSupport {
    private String count;


    public void setCount(String count) {
        this.count = count;
        System.out.println("serCount voi count = " + count);
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
            int nCount = 0;
        try {
            nCount = Integer.parseInt(count);
        } catch (NumberFormatException ex) {
            nCount = 8;
        }
        for (int i = 0; i < nCount; i++) {
            try {
                out.println(i + ", ");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        return EVAL_PAGE;
    }
}
