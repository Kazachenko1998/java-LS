package LS;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ls {

    private static String accessBite(File file) {
        String result = new String();
        if (file.canExecute() == true) result += "1";
        else result += "0";
        if (file.canRead() == true) result += "1";
        else result += "0";
        if (file.canWrite() == true) result += "1";
        else result += "0";
        return result;
    }

    private static String accessStr(File file) {
        String result = new String();
        if (file.canExecute() == true) result += "x";
        else result += "-";
        if (file.canRead() == true) result += "r";
        else result += "-";
        if (file.canWrite() == true) result += "w";
        else result += "-";
        return result;
    }

    private static String sizeFile(File file) {
        long size = file.length();
        String result = new String();
        if (file.isDirectory()) result = "Directory ";
        if (size / (1024 * 1024 * 1024) > 0) return result + (int) (size / 1024 / 1024 / 1024) + "GB";
        else if (size / (1024 * 1024) > 0) return result + (int) (size / 1024 / 1024) + "MB";
        else if (size / 1024 > 0) return result + (int) (size / 1024) + "KB";
        else
            return result + (int) (size) + "Bait";
    }

    private static String data(File file) {
        Date date = new Date(file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return "  last modification " + sdf.format(date) + "  ";
    }

    private static String outPut(File file) {
        String result = new String();
        String[] str = file.list();
        int size = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i].length() > size) size = str[i].length();
        }
        for (int i = 0; i < str.length; i++) {
            str[i] = file.getPath() + "\\" + str[i];
            String line = new String();
            for (int j = str[i].length(); j < size + 1; j++)
                line += " ";
            result = result + str[i] + line +
                    accessStr(new File(str[i])) +
                    data(new File(str[i])) +
                    sizeFile(new File(str[i])) + "\n";
        }
        return result;
    }

    public static void main(String[] D) {
        File one = new File("C:\\Windows");
        String input = new String();
        System.out.print(outPut(one));
    }
}
