package LS;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ls {

    interface FileFormatter {
        String makeString(File f, String spaces);
    }

    static class FileInterface implements FileFormatter {

        FileInterface() {
        }

        public String makeString(File file, String string) {
            return file.getName();
        }
    }

    static class FileInterfaceL implements FileFormatter {


        FileInterfaceL() {
        }

        String accessByte(File file) {
            String result = "";
            if (file.canExecute()) result += "1";
            else result += "0";
            if (file.canRead()) result += "1";
            else result += "0";
            if (file.canWrite()) result += "1";
            else result += "0";
            return result;
        }

        String sizeFile(File file) {
            long size = file.length();
            return "" + size;
        }

        @Override
        public String makeString(File file, String spaces) {
            return
                    file.getPath() + spaces + "  " +
                            accessByte(file) + " "
                            + file.lastModified() + " "
                            + sizeFile(file);
        }
    }

    static class FileInterfaceLH implements FileFormatter {


        FileInterfaceLH() {
        }

        String data(File file) {
            Date date = new Date(file.lastModified());
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
            return "  последнее изменение " + sdf.format(date) + "  ";
        }


        String accessHuman(File file) {
            String result = "";
            if (file.canExecute()) result += "x";
            else result += "-";
            if (file.canRead()) result += "r";
            else result += "-";
            if (file.canWrite()) result += "w";
            else result += "-";
            return result;
        }

        String sizeFileHuman(File file) {
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
        public String makeString(File file, String spaces) {
            return
                    file.getName() + spaces + "  " +
                            accessHuman(file) + " "
                            + data(file) + " "
                            + sizeFileHuman(file);
        }
    }


    @SuppressWarnings("ConstantConditions")
    static ArrayList<String> makeListing(File fileOrDirectory, FileFormatter formatter) {
        ArrayList<String> list = new ArrayList<>();
        String size = "";
        if (fileOrDirectory.isFile()) {
            list.add(formatter.makeString(fileOrDirectory, ""));
            return list;
        } else {
            for (String aStr : fileOrDirectory.list()) if (aStr.length() > size.length()) size = aStr;
            for (String aStr : fileOrDirectory.list()) {
                StringBuilder space = new StringBuilder();
                for (int j = aStr.length(); j < size.length(); j++)
                    space.append(" ");
                list.add(formatter.makeString(new File(fileOrDirectory.getPath() + "\\" + aStr), space.toString()));
            }
        }
        return list;
    }


    public static void commandLine(String[] line) throws FileNotFoundException {
        FlagArg flagArg = new FlagArg(line);
        if (line.length == 0) throw new IllegalArgumentException("неверная команда");
        StringBuilder result = new StringBuilder();
        File file = new File(flagArg.getInput());
        ArrayList<String> list;
        if (!file.exists())
            throw new NullPointerException("неверный входной путь");
        if ((flagArg.isH()) && (flagArg.isL())) list = makeListing(file, new FileInterfaceLH());
        else if (flagArg.isL()) list = makeListing(file, new FileInterfaceL());
        else
            list = makeListing(file, new FileInterface());
        if (flagArg.isR()) for (int i = list.size() - 1; i >= 0; i--)
            result.append(list.get(i)).append("\n");
        else for (String aList : list) result.append(aList).append("\n");
        if (flagArg.getOutput() == null) {
            try (PrintWriter pw = new PrintWriter(System.out)) {
                pw.print(result.toString());
            }
        } else {
            try (PrintWriter pw = new PrintWriter(flagArg.getOutput())) {
                pw.print(result.toString());
            }
        }
    }

    public static void main(String[] D) throws Exception {
        commandLine(D);
    }
}