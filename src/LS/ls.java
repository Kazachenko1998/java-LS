package LS;

import java.io.*;
import java.util.ArrayList;

public class ls {
    @SuppressWarnings("ConstantConditions")
    private static ArrayList<String> makeListing(File fileOrDirectory, FileFormat.FileFormatter formatter) {
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
        if ((flagArg.isH()) && (flagArg.isL())) list = makeListing(file, new FileFormat.FileInterfaceLH());
        else if (flagArg.isL()) list = makeListing(file, new FileFormat.FileInterfaceL());
        else
            list = makeListing(file, new FileFormat.FileInterface());
        if (flagArg.isR()) for (int i = list.size() - 1; i >= 0; i--)
            result.append(list.get(i)).append("\n");
        else for (String aList : list) result.append(aList).append("\n");
        if (flagArg.getOutput() == null) {
            try (PrintWriter pw = new PrintWriter(System.out)) {
                pw.print(result.toString());
            }
        } else {
            try (PrintWriter pw = new PrintWriter(flagArg.getOutput()))  {
                pw.print(result.toString());
            }catch (java.io.FileNotFoundException ex){throw new IllegalArgumentException("неверное имя выходного файла");}
        }
    }

    public static void main(String[] D) throws Exception {
        commandLine(D);
    }
}