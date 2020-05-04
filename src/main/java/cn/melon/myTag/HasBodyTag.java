package cn.melon.myTag;

import cn.melon.model.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashMap;

public class HasBodyTag extends SimpleTagSupport {
    private String map;

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    @Override
    public void doTag() throws JspException, IOException {
        HashMap<String,Integer> maps = (HashMap<String,Integer>)(getJspContext().getAttribute(map));
        for (String str : maps.keySet()){
            getJspContext().setAttribute("name",str);
            getJspContext().setAttribute("age",maps.get(str));
            getJspBody().invoke(null);
        }
    }
}
