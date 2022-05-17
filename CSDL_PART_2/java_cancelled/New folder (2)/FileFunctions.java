package demo;

import java.util.*;
import java.io.*;

public class FileFunctions {
    ArrayList<String> dataList = new ArrayList<String>();

    public ArrayList<String> getDataList() {
        return this.dataList;
    }

    public void setDataList(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    public FileFunctions(String path) {
        dataList = readFile(path);
    }

    public ArrayList<String> readFile(String path) {
        ArrayList<String> tempList = new ArrayList<String>();
        File file = new File(path);
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) { // get line and check for end of file
                tempList.add(line.trim()); // add line to arrayList
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally { // a "finally" to close the buffer reader
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // System.out.println("\t\tBuffer Reader:");
        // for (String i : tempList) {
        // System.out.println(i);
        // }
        return tempList;
    }

}
