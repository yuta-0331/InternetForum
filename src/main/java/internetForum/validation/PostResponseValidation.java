package internetForum.validation;

// 返信フォームの入力チェック
public class PostResponseValidation {
    
    public boolean validation(String desc) {
        return !desc.equals("");
    }
}
