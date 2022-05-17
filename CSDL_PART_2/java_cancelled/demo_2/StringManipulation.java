
// import java.lang.reflect.Array;
import java.util.*;

public class StringManipulation {
    String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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

    public StringManipulation(String big_string) {
        String[] t = big_string.split("_");
        // System.out.println(t[0]);
        // System.out.println(t[0].indexOf('{'));
        String[] t0 = t[0].substring(t[0].indexOf("{") + 1, t[0].length() - 1).split(",");
        String[] t1 = t[1].substring(t[1].indexOf("{") + 1, t[1].length() - 1).split(";");



        // System.out.println(Arrays.toString(t0));
        // System.out.println(Arrays.toString(t1));

        // // HashMap<String, String> attributeAlphabet = new HashMap<String, String>();
        // Map<String, String> attributeAlphabet = new LinkedHashMap<String, String>();
        // for (int i = 0; i < t0.length; i++) {
        //     attributeAlphabet.put(t0[i], Character.toString(ALPHABET.charAt(i)));
        //     // System.out.println(t0[i]);
        // }
        // System.out.println(attributeAlphabet);
        // // System.out.println(t0.getClass());
        // String[] t00 = t0.clone();
        // for (int i = 0; i < t00.length; i++) {
        //     String temp = attributeAlphabet.get(t00[i]);
        //     t00[i] = temp;
        // }
        // System.out.println(Arrays.toString(t00));
        // // String[] t11 = t1.clone();

        // List<String> t11 = Arrays.asList(t1.clone());
        // for (String string : t11) {
        //     String[] o1 = string.split("-");
        //     System.out.println(Arrays.toString(o1));
        //     String left = o1[0];
        //     String right = o1[1];
        //     if ((left.length() > 1) && (right.length() == 1)) {
        //         String[] temp = left.split("+");
        //         for (int i = 0; i < temp.length; i++) {
        //             temp[i]
        //         } 
        //     }
        //     // for (String string2 : o1) {
        //     // System.out.println(string2[0]);
        //     // // System.out.println(string2);
        //     // }
        //     // System.out.println(string);
        // }

    }

}
