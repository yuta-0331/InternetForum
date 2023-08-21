package internetForum.validation;

// クエリパラメータが数値かどうかチェック
public class NumberValidation {
    public boolean isInteger(String s) {
        if (s == null) return false;;
        if (!s.equals("")) {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

}
