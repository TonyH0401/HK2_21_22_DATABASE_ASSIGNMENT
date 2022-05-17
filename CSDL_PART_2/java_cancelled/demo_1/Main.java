package demo;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileFunctions file = new FileFunctions("D:\\TDTU\\CSDL\\src\\Input2.txt");
        StringEdit sEdit = new StringEdit(file.getDataList().toString());

        // Q2Funtions q2f = new Q2Funtions(sEdit.gettable_origin(), sEdit.getKey());
        // System.out.println(q2f.getKey());
        // System.out.println(sEdit.keyConvert(q2f.getKey()));
        // System.out.println();
        
        String temp = sEdit.user_input_checkString("Name,BirthYear");
        System.out.println(temp);

        // Q2Funtions q2f = new Q2Funtions(sEdit.gettable_origin(), sEdit.getKey(), "BC");
        // System.out.println(q2f.getClosure());
        // System.out.println(sEdit.closureConvert(q2f.getClosure()));

    }
}
