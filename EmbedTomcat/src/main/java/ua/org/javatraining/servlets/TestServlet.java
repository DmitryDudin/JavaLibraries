package ua.org.javatraining.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class TestServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(TestServlet.class);

    public TestServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.getWriter().println("welcome!");
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Writer w = resp.getWriter();
        w.write("Hello, this is TestServlet!!!\n");
        w.flush();
        w.close();
    }

}


