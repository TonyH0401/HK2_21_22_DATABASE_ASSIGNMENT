import java.lang.reflect.Array;
import java.util.*;;

public class Q2Functions {
    // public static void main(String[] args) {
    // // String[] R = { "A", "B", "C", "D", "E", "F" };
    // // String input = "AB";

    // // String[] R = { "A", "B", "C", "D", "E", "G", "H" };
    // // String[] F = { "B-A", "AD-CE", "D-H", "GH-C", "CA-D" };
    // // String input = "AC";

    // String[] R = { "A", "B", "C", "D", "E" };
    // String[] F = { "AB-C", "AC-B", "BC-DE" };
    // // String input = "AC";

    // // String res = closure_loop(input, F);
    // // System.out.println("Result: " + res);

    // String res = find_key_in_entity(R, F);
    // System.out.println("Result: " + res);
    // }
    String closure = "";
    String key = "";

    public String getClosure() {
        return closure;
    }

    public void setClosure(String closure) {
        this.closure = closure;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Q2Functions(String[] R, String[] F, String input) {
        closure = closure_loop(input, F);
        key = find_key_in_entity(R, F);
    }

    public String find_key_in_entity(String[] R, String[] F) {
        String Q = String.join(",", R).replace(",", "");
        String K = Q.toString();
        System.out.println(K);
        // String temp = R_input;
        // String res = "";
        for (int i = 0; i < R.length; i++) {
            // System.out.println(true);
            String removed_char = R[i];
            String temp = K.replace(removed_char, "");
            String bao_dong = closure_loop(temp, F);
            // kiem tra bao dong
            if (bao_dong.length() == Q.length()) {
                boolean verify_token = true;
                for (int j = 0; j < bao_dong.length(); j++) {
                    if (!Q.contains(Character.toString(bao_dong.charAt(j)))) {
                        verify_token = false;
                        break;
                    }
                }
                // update K
                if (verify_token) {
                    K = temp;
                    // System.out.println(K);
                }
            }

        }
        return K;
    }

    public String closure_loop(String small_input, String[] F) {
        String res = small_input.toString();
        String saved = "";

        while (true) {
            if (saved.equals(res)) {
                break;
            }

            saved = res;

            for (String index : F) {
                String[] t = index.split("-");
                String leftSide = t[0];
                String rightSide = t[1];
                boolean verify_token = true;
                List<String> leftarray = new ArrayList<String>();
                List<String> rightarray = new ArrayList<String>();
                // String[] left_array = new String[10];
                // String[] right_array = new String[10];

                if (leftSide.contains("+")) {
                    leftarray = Arrays.asList(leftSide.split("+"));
                }
                if (rightSide.contains("+")) {
                    rightarray = Arrays.asList(rightSide.split("+"));
                    // right_array = rightSide.split("+");
                }
                if ((leftarray.size() > 1) && (rightarray.size() > 1)) {
                    for (String string : leftarray) {
                        if (!res.contains(string)) {
                            verify_token = false;
                            break;
                        }
                    }
                    if (verify_token) {
                        for (String string : rightarray) {
                            if (!res.contains(string)) {
                                res = res + string;
                                res = removeDuplicate(res.toCharArray(), res.length());
                            }
                        }
                    }
                } else if ((leftarray.size() > 1) && (rightarray.size() == 1)) {
                    for (String string : leftarray) {
                        if (!res.contains(string)) {
                            verify_token = false;
                            break;
                        }
                    }
                    if ((verify_token == true) && (!res.contains(rightSide))) {
                        res = res + rightSide;
                        res = removeDuplicate(res.toCharArray(), res.length());
                    }

                } else if ((leftarray.size() == 1) && (rightarray.size() > 1)) {
                    if (!res.contains(leftSide)) {
                        verify_token = false;
                    }
                    if (verify_token == true) {
                        for (String string : rightarray) {
                            if (!res.contains(string)) {
                                res = res + string;
                                res = removeDuplicate(res.toCharArray(), res.length());
                            }
                        }
                    }
                } else {
                    if (!res.contains(leftSide)) {
                        verify_token = false;
                    }
                    if ((verify_token == true) && (!res.contains(rightSide))) {
                        res = res + rightSide;
                        res = removeDuplicate(res.toCharArray(), res.length());
                    }
                }

                // for (int i = 0; i < leftSide.length(); i++) {
                //     char l = leftSide.charAt(i);
                //     if (!res.contains(Character.toString(l))) {
                //         verify_token = false;
                //         break;
                //     }
                // }

                // if (verify_token) {
                //     res = res + rightSide;
                //     res = removeDuplicate(res.toCharArray(), res.length());
                // }
            }

        }
        return res;
    }

    public String removeDuplicate(char str[], int n) {
        // Used as index in the modified string
        int index = 0;

        // Traverse through all characters
        for (int i = 0; i < n; i++) {

            // Check if str[i] is present before it
            int j;
            for (j = 0; j < i; j++) {
                if (str[i] == str[j]) {
                    break;
                }
            }

            // If not present, then add it to
            // result.
            if (j == i) {
                str[index++] = str[i];
            }
        }
        return String.valueOf(Arrays.copyOf(str, index));
    }

}