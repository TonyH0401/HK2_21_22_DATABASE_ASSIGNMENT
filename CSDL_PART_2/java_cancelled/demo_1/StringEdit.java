package demo;

import java.util.*;

public class StringEdit {
    String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String[] table_origin;
    String[] key;
    Map<String, String> dictMap = new LinkedHashMap<String, String>();
    String[] original_table;

    public String[] gettable_origin() {
        return table_origin;
    }

    public void settable_origin(String[] table_origin) {
        this.table_origin = table_origin;
    }

    public String[] getKey() {
        return key;
    }

    public void setKey(String[] key) {
        this.key = key;
    }

    public StringEdit(String big_string) {
        String[] t = big_string.split("_");

        // find and replace attributes with the ALPHABET
        String[] table = t[0].substring(t[0].indexOf("{") + 1, t[0].length() - 1).split(",");
        // System.out.println(Arrays.toString(table));
        Map<String, String> attributeAlphabet = new LinkedHashMap<String, String>();
        for (int i = 0; i < table.length; i++) {
            attributeAlphabet.put(table[i], Character.toString(ALPHABET.charAt(i)));
        }
        // System.out.println(attributeAlphabet);
        dictMap = attributeAlphabet;

        // replace the dependency function STRING with ALPHABET
        String depend_func = t[1].substring(t[1].indexOf("{") + 1, t[1].length() - 2);
        // System.out.println(depend_func);
        String depend_func_copy = new String(depend_func.trim());
        for (Map.Entry<String, String> entry : attributeAlphabet.entrySet()) {
            depend_func_copy = depend_func_copy.replace(entry.getKey(), entry.getValue());
            // System.out.println("Key: " + entry.getKey() + " - Value: " +
            // entry.getValue());
        }
        depend_func_copy = depend_func_copy.replace("+", "");
        // System.out.println(depend_func_copy);
        String[] depend_func_array = depend_func_copy.split(";");
        // System.out.println(Arrays.toString(depend_func_array));

        // replace the table with ALPHABET
        String entity = t[0].substring(t[0].indexOf("{") + 1, t[0].length() - 1);
        for (Map.Entry<String, String> entry : attributeAlphabet.entrySet()) {
            entity = entity.replace(entry.getKey(), entry.getValue());
        }
        String[] entity_array = entity.split(",");
        // System.out.println(entity);

        table_origin = entity_array;
        key = depend_func_array;
        // System.out.println(Arrays.toString(table_origin));

        original_table = table;
    }

    public String keyConvert(String key) {
        String key_copy = key.toString();
        String res = "";
        for (int i = 0; i < key_copy.length(); i++) {
            String temp = "";
            for (Map.Entry<String, String> entry : dictMap.entrySet()) {
                if (Character.toString(key_copy.charAt(i)).equals(entry.getValue())) {
                    temp = entry.getKey();
                    break;
                }
            }
            res = res + temp + ",";
        }
        return res.substring(0, res.length() - 1);
    }

    public String closureConvert(String closure) {
        String closure_copy = closure.toString();
        String res = "";
        for (int i = 0; i < closure_copy.length(); i++) {
            String temp = "";
            for (Map.Entry<String, String> entry : dictMap.entrySet()) {
                if (Character.toString(closure_copy.charAt(i)).equals(entry.getValue())) {
                    temp = entry.getKey();
                    break;
                }
            }
            res = res + temp + ",";
        }
        return res.substring(0, res.length() - 1);
    }

    public String user_input_checkString(String user_attribute) {
        List<String> demoList = new ArrayList<String>();
        String user_attribute_copy = user_attribute.toString().replace(" ", "").trim();
        String[] t = user_attribute.trim().replace(" ", "").split(",");
        
        demoList = Arrays.asList(original_table);
        boolean token = true;
        for (String string : t) {
            if (!demoList.contains(string)) {
                token = false;
                break;
            }
        }
        if (token == false) {
            return null;
        } else {
            for (Map.Entry<String, String> entry : dictMap.entrySet()) {
                user_attribute_copy = user_attribute_copy.replace(entry.getKey(), entry.getValue());
                // System.out.println("Key: " + entry.getKey() + " - Value: " +
                // entry.getValue());
            }
            return user_attribute_copy.replace(",", "").trim();
        }
    }

}
