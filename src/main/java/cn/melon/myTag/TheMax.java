package cn.melon.myTag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class TheMax extends SimpleTagSupport {
    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public void doTag() throws JspException, IOException {
//        int aa = (int) getJspContext().getAttribute(a);
//        int bb = (int)getJspContext().getAttribute(b);
        getJspContext().getOut().println(Math.max(a, b));
    }
}
