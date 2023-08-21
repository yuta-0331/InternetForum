package internetForum.validation;

import javax.servlet.http.HttpSession;

public class LoginValidation {

    public boolean valid(HttpSession session) {
        return session != null && (Integer) session.getAttribute("loginSession") != null;
    }
}
