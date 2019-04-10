package next.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(name = "dispatcher" , urlPatterns = "/",loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ControllerMapping.initController();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    }
}
