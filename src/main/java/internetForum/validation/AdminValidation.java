package internetForum.validation;

import javax.servlet.http.HttpSession;

public class AdminValidation {
    public boolean valid(HttpSession session) {
        return session != null && session.getAttribute("adminSession") != null && (Integer) session.getAttribute("adminSession") != 0;
    }

}
