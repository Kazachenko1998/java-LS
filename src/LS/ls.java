package LS;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class ls {

    private static String accessByte(File file) {
        String result = "";
        if (file.canExecute()) result += "1";
        else result += "0";
        if (file.canRead()) result += "1";
        else result += "0";
        if (file.canWrite()) result += "1";
        else result += "0";
        return result;
    }

    private static String accessHuman(File file) {
        String result = "";
        if (file.canExecute()) result += "x";
        else result += "-";
        if (file.canRead()) result += "r";
        else result += "-";
        if (file.canWrite()) result += "w";
        else result += "-";
        return result;
    }

    private static String sizeFileHuman(File file) {
        long size = file.length();
        String result = "";
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
        if (file.isDirectory()) {
            Collections.addAll(list, str);
        }
        if (file.isFile()) list.add(file.getPath());
        return list;
    }

    private static ArrayList<String> builderL(File file) {
        String[] str = file.list();
        ArrayList<String> list = new ArrayList<>();
        int size = 0;
        if (file.isDirectory()) {
            for (String aStr : str) if (aStr.length() > size) size = aStr.length();
        }
        if (file.isDirectory()) {
            for (String aStr : str) {
                StringBuilder space = new StringBuilder();
                for (int j = aStr.length(); j < size + 1; j++)
                    space.append(" ");
                list.add(
                        file.getPath() + "\\" + aStr + space + " " +
                                accessByte(new File(file.getPath() + "\\" + aStr)) + " "
                                + (new File(file.getPath() + "\\" + aStr)).lastModified() + " "
                                + sizeFile(new File(file.getPath() + "\\" + aStr)));
            }
        }
        if (file.isFile()) {
            list.add(
                    file.getPath() + "  " +
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

        if (file.isDirectory()) {
            for (String aStr : str) if (aStr.length() > size) size = aStr.length();
        }
        if (file.isDirectory()) {
            for (String aStr : str) {
                StringBuilder space = new StringBuilder();
                for (int j = aStr.length(); j < size + 1; j++)
                    space.append(" ");
                list.add(
                        aStr + space + " " +
                                accessHuman(new File(file.getPath() + "\\" + aStr)) + " "
                                + data(new File(file.getPath() + "\\" + aStr)) + " "
                                + sizeFileHuman(new File(file.getPath() + "\\" + aStr)));
            }
        }
        if (file.isFile()) {
            list.add(
                    file.getPath() + "  " +
                            accessHuman(file) + " "
                            + data(file) + " "
                            + sizeFileHuman(file));
        }
        return list;
    }

    private static String commandLine(String[] line) {
        FlagArg flagArg = new FlagArg(line);
        if (line.length == 0) throw new IllegalArgumentException("неверная команда");
        StringBuilder result = new StringBuilder();
        File file = new File(flagArg.getInput());
        ArrayList<String> list;
        if (!file.exists())
            throw new NullPointerException("неверный входной путь");
        if ((flagArg.isH()) && (flagArg.isL())) list = builderLH(file);
        else if (flagArg.isL()) list = builderL(file);
        else
            list = builder(file);
        if (flagArg.isR()) for (int i = list.size() - 1; i >= 0; i--)
            result.append(list.get(i)).append("\n");
        else for (String aList : list) result.append(aList).append("\n");
        if (flagArg.getOutput() == null)
            return result.toString();
        else {
            try (FileWriter writer = new FileWriter(flagArg.getOutput())) {
                writer.write(result.toString());
                writer.flush();
            } catch (IOException ex) {
                throw new NullPointerException("неверный выходной путь");
            }
        }
        return "";
    }

    public static String test(String str) {
        return commandLine(str.split(" "));
    }

    public static void main(String[] D) throws Exception {
        System.out.print(commandLine(D));
    }
}
