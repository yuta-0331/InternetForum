package internetForum.filter;

import javax.servlet.http.HttpSession;
import java.util.UUID;

public class CsrfTokenUtil {
    // UUIDをcsrf tokenとしてセッションに付与する
    public String createAndStore(HttpSession session) {
        String token = UUID.randomUUID().toString();
        session.setAttribute("csrfToken", token);
        return token;
    }
    // csrf tokenによるバリデーション(引数で与えたtokenがセッションのtokenと一致するか)
    public boolean isValid(HttpSession session, String token) {
        String sessionToken = (String) session.getAttribute("csrfToken");
        return sessionToken != null && sessionToken.equals(token);
    }
}
