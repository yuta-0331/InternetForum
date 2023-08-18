package internetForum.validation;

// クエリパラメータが数値かどうかチェック
public class NumberValidation {
    public static boolean isInteger(String s) {
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
