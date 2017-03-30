package LS;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ls {

    private static String accessByte(File file) {
        String result = new String();
        if (file.canExecute() == true) result += "1";
        else result += "0";
        if (file.canRead() == true) result += "1";
        else result += "0";
        if (file.canWrite() == true) result += "1";
        else result += "0";
        return result;
    }

    private static String accessHuman(File file) {
        String result = new String();
        if (file.canExecute() == true) result += "x";
        else result += "-";
        if (file.canRead() == true) result += "r";
        else result += "-";
        if (file.canWrite() == true) result += "w";
        else result += "-";
        return result;
    }

    private static String sizeFileHuman(File file) {
        long size = file.length();
        String result = new String();
        if (file.isDirectory()) result = "(Папка) ";
        if (size / (1024 * 1024 * 1024) > 0) return result + (int) (size / 1024 / 1024 / 1024) + "GB";
        else if (size / (1024 * 1024) > 0) return result + (int) (size / 1024 / 1024) + "MB";
        else if (size / 1024 > 0) return result + (int) (size / 1024) + "KB";
        else
            return result + (size) + "Byte";
    }

    private static String sizeFile(File file) {
        long size = file.length();
        return "" + size;
    }

    private static String data(File file) {
        Date date = new Date(file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return "  последнее изменение " + sdf.format(date) + "  ";
    }

    private static ArrayList<String> builder(File file) {
        ArrayList<String> list = new ArrayList<>();
        String[] str = file.list();
        if (file.isDirectory()) for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
             }
        if (file.isFile()) list.add(file.getPath());
        return list;
    }

    private static ArrayList<String> builderL(File file) {
        String[] str = file.list();
        ArrayList<String> list = new ArrayList<>();
        int size = 0;
          if (file.isDirectory())  for (int i = 0; i < str.length; i++)
                if (str[i].length() > size) size = str[i].length();
        if (file.isDirectory()) for (int i = 0; i < str.length; i++) {
            String space = new String();
            for (int j = str[i].length(); j < size + 1; j++)
                space += " ";
            list.add(
                    file.getPath() + "\\" + str[i] + space + " " +
                            accessByte(new File(file.getPath() + "\\" + str[i])) + " "
                            + (new File(file.getPath() + "\\" + str[i])).lastModified() + " "
                            + sizeFile(new File(file.getPath() + "\\" + str[i])));
        }
        if (file.isFile()) {
            list.add(
                    file.getPath()  + "  " +
                            accessByte(file) + " "
                            + file.lastModified() + " "
                            + sizeFile(file));
            }
        return list;
    }

    private static ArrayList<String> builderLH(File file) {
        ArrayList<String> list = new ArrayList<>();
        String[] str = file.list();
        int size = 0;

         if (file.isDirectory())  for (int i = 0; i < str.length; i++)
                if (str[i].length() > size) size = str[i].length();
        if (file.isDirectory()) for (int i = 0; i < str.length; i++) {
            String space = new String();
            for (int j = str[i].length(); j < size + 1; j++)
                space += " ";
           list.add(
                    str[i] + space + " " +
                            accessHuman(new File(file.getPath() + "\\" + str[i])) + " "
                            + data(new File(file.getPath() + "\\" + str[i])) + " "
                            + sizeFileHuman(new File(file.getPath() + "\\" + str[i])));
        }
        if (file.isFile())  {
           list.add(
                   file.getPath() +  "  " +
                            accessHuman(file) + " "
                            + data(file) + " "
                            + sizeFileHuman(file));
        }
        return list;
    }

    private static String commandLine(String[] line) {
        FlagArg flagArg = new FlagArg(line);
        if (line.length == 0) throw new IllegalArgumentException("неверная команда");
        String result = new String();
        File file = new File(flagArg.getInput());
        String[] str = file.list();
        ArrayList<String> list;
        if (file.isDirectory())
        try {
            for (int i = 0; i < str.length; i++) {
                if (str[i].length() > 0);
            }
        } catch (NullPointerException ex) {
            throw new NullPointerException("неверный входной путь");
        }

        if ((flagArg.isH())&&(flagArg.isL())) list = builderLH(file); else
        if (flagArg.isL()) list = builderL(file);else
            list = builder(file);
        if (flagArg.isR()) for (int i = list.size() - 1; i >= 0; i--)
            result += list.get(i) + "\n";
        else for (int i = 0; i < list.size(); i++)
            result += list.get(i) + "\n";
        if (flagArg.getOutput() == null)
            return result;
        else {
            try (FileWriter writer = new FileWriter(flagArg.getOutput())) {
                writer.write(result);
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return "";
    }

public static String test(String str){
        return commandLine(str.split(" "));
}

    public static void main(String[] D) throws Exception {
      System.out.print(commandLine(D));
    }
}
