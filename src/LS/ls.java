package LS;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ls {


    private static void PrintWriter(String file, String out) {
        if (out == null) System.out.println(file);
        else {
            try (FileWriter writer = new FileWriter(out)) {
                writer.write(file);
                writer.flush();
            } catch (IOException ex) {
                throw new NullPointerException("неверный выходной путь");
            }
        }
    }

    interface FileFormatter {
        String makeString(File f);

        String makeStringL(File f, String space);

        String accessByte(File file);

        String sizeFile(File file);

        String data(File file);

        String makeStringLH(File f, String space);

        String accessHuman(File file);

        String sizeFileHuman(File file);
    }

    static class FileInterface implements FileFormatter {
        private String str;

        public FileInterface(File file, Integer type, String space) {
            if (type == 0) str = makeString(file);
            if (type == 1) str = makeStringL(file, space);
            if (type == 2) str = makeStringLH(file, space);
        }


        @Override
        public String accessByte(File file) {
            String result = "";
            if (file.canExecute()) result += "1";
            else result += "0";
            if (file.canRead()) result += "1";
            else result += "0";
            if (file.canWrite()) result += "1";
            else result += "0";
            return result;
        }

        @Override
        public String sizeFile(File file) {
            long size = file.length();
            return "" + size;
        }

        @Override
        public String data(File file) {
            Date date = new Date(file.lastModified());
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
            return "  последнее изменение " + sdf.format(date) + "  ";
        }


        @Override
        public String accessHuman(File file) {
            String result = "";
            if (file.canExecute()) result += "x";
            else result += "-";
            if (file.canRead()) result += "r";
            else result += "-";
            if (file.canWrite()) result += "w";
            else result += "-";
            return result;
        }

        @Override
        public String sizeFileHuman(File file) {
            long size = file.length();
            String result = "";
            if (file.isDirectory()) result = "(Папка) ";
            if (size / (1024 * 1024 * 1024) > 0) return result + (int) (size / 1024 / 1024 / 1024) + "GB";
            else if (size / (1024 * 1024) > 0) return result + (int) (size / 1024 / 1024) + "MB";
            else if (size / 1024 > 0) return result + (int) (size / 1024) + "KB";
            else
                return result + (size) + "Byte";
        }

        @Override
        public String makeStringL(File file, String space) {
            return
                    file.getPath()+ space + " " +
                            accessByte(file) + " "
                            + file.lastModified() + " "
                            + sizeFile(file);
        }

        @Override
        public String makeStringLH(File file, String space) {

            return
                    file.getName() + space + " " +
                            accessHuman(file) + " "
                            + data(file) + " "
                            + sizeFileHuman(file);
        }

        @Override
        public String makeString(File file) {
            return file.getName();
        }

        @Override
        public String toString() {
            return str;
        }

    }


    @SuppressWarnings("ConstantConditions")
    public static ArrayList<String> makeListing(File fileOrDirectory, Integer type) {
        ArrayList<String> list = new ArrayList<>();
        int size = 0;
        if (fileOrDirectory.isFile()) {
            list.add(new FileInterface(fileOrDirectory, type, "").toString());
            return list;
        } else {
            for (String aStr : fileOrDirectory.list()) if (aStr.length() > size) size = aStr.length();
            for (String aStr : fileOrDirectory.list()) {
                StringBuilder space = new StringBuilder();
                for (int j = aStr.length(); j < size + 1; j++)
                    space.append(" ");
                list.add(new FileInterface(new File(fileOrDirectory.getPath() + "\\" + aStr), type, space.toString()).toString());
            }
        }
        return list;
    }


    public static void commandLine(String[] line) {
        FlagArg flagArg = new FlagArg(line);
        if (line.length == 0) throw new IllegalArgumentException("неверная команда");
        StringBuilder result = new StringBuilder();
        File file = new File(flagArg.getInput());
        ArrayList<String> list;
        if (!file.exists())
            throw new NullPointerException("неверный входной путь");
        if ((flagArg.isH()) && (flagArg.isL())) list = makeListing(file, 2);
        else if (flagArg.isL()) list = makeListing(file, 1);
        else
            list = makeListing(file, 0);
        if (flagArg.isR()) for (int i = list.size() - 1; i >= 0; i--)
            result.append(list.get(i)).append("\n");
        else for (String aList : list) result.append(aList).append("\n");
        PrintWriter(result.toString(), flagArg.getOutput());
    }

    public static void main(String[] D) throws Exception {
        commandLine(D);
    }
}