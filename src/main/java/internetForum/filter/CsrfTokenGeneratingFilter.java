package internetForum.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/*")
public class CsrfTokenGeneratingFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {}

    public void destroy(ServletRequest request, ServletResponse response) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // GET methodがリクエストされた時、リクエストスコープにcsrf tokenを付与する.
        if ("GET".equalsIgnoreCase(httpServletRequest.getMethod())) {
            String token = new CsrfTokenUtil().createAndStore(httpServletRequest.getSession(true));
            httpServletRequest.setAttribute("csrfToken", token);
        }
        chain.doFilter(request, response);
    }
}