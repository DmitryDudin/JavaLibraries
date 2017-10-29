package ua.org.javatraining.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter("/*")
public class TestFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(TestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        int i = 0;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("TestFilter before request");
        LOG.info("TestFilter after request");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        int i = 0;

    }
}
