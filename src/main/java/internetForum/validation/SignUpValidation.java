package internetForum.validation;

// パスワードの文字数等サインアップフォームの入力チェック
public class SignUpValidation {
    private final int MAX_PASS_LENGTH = 16;
    private final int MIN_PASS_LENGTH = 8;
    
    public boolean validation(String userName, String mailAddress, String password) {
        return !mailAddress.equals("") && !userName.equals("") && password.length() >= MIN_PASS_LENGTH && password.length() <= MAX_PASS_LENGTH;
    }
}
