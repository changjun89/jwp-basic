package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
    private String viewName;

    public ForwardController(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (viewName == null) {
            throw new IllegalStateException("viewname 이 없습니다.");
        }
        return this.viewName;
    }
}
