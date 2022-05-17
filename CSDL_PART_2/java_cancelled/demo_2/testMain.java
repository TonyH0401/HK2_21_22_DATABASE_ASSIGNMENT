import java.util.*;
import java.io.*;

public class testMain {
    public static void main(String[] args) {
        FileFunctions file = new FileFunctions("D:\\TDTU\\CSDL\\src\\Input2.txt");
        ArrayList<String> data = file.getDataList();

        for (String string : data) {
            // System.out.println(string);
            StringManipulation sm = new StringManipulation(string);
            // System.out.println("test: " + sm.getKey());

            String[] R = {"SID","Name","BirthYear","Faculty","Score"};
            String[] F = {"SID+Name-BirthYear","SID+BirthYear-Name","Name+BirthYear-Faculty+Score"};

            Q2Functions Q2 = new Q2Functions(R, F, "SID");

        }
    }
}
