package internetForum.validation;

// スレッド作成フォームの入力チェック
public class CreateThreadValidation {
    public boolean validation(String title, String desc) {
        return !title.equals("") && !desc.equals("");
    }
}
