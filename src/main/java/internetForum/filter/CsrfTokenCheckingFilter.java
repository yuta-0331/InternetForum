package internetForum.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/*")
public class CsrfTokenCheckingFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {}

    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        request.setCharacterEncoding("UTF-8");
        // requestがPOST methodの場合、csrf tokenのバリデーションをかける
        if ("POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            String tokenFromForm = httpServletRequest.getParameter("csrfToken");
            if (!new CsrfTokenUtil().isValid(httpServletRequest.getSession(true), tokenFromForm)) {
                throw new ServletException("csrf tokeが無効です");
            }
        }
        chain.doFilter(request, response);
    }
}