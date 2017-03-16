package LS;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ls {

    private static String accessBite(File file){
        String result = new String();
        if (file.canExecute()==true) result+="1"; else result+="0";
        if (file.canRead()==true) result+="1"; else result+="0";
        if (file.canWrite()==true) result+="1"; else result+="0";
return result;
    }
    private static String accessStr(File file){
        String result = new String();
        if (file.canExecute()==true) result+="x"; else result+="-";
        if (file.canRead()==true) result+="r"; else result+="-";
        if (file.canWrite()==true) result+="w"; else result+="-";
        return result;
    }
    public static void main(String[] D) {

        File one = new File("C:\\Windows");
        String[] str = one.list();
        int size = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i].length() > size) size = str[i].length();
        }
        for (int i = 0; i < str.length; i++) {
            str[i] = one.getPath() + "\\" + str[i];
            String line = new String();
            Date date = new Date(new File(str[i]).lastModified());
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
            for (int j = str[i].length(); j < size + 1; j++)
                line += " ";
str[i]= str[i]+line+accessStr(new File(str[i])) + "  last modification "+sdf.format(date)+"  "+(new File(str[i])).length();
            System.out.println(str[i]);
        }
    }
}
