package LS;

import java.io.*;
import java.util.ArrayList;

public class ls {
    @SuppressWarnings("ConstantConditions")
    private static ArrayList<String> makeListing(File fileOrDirectory, FileFormat.FileFormatter formatter) {
        ArrayList<String> list = new ArrayList<>();
        if (fileOrDirectory.isFile()) {
            list.add(formatter.makeString(fileOrDirectory));
            return list;
        } else {
            for (String aStr : fileOrDirectory.list()) {
                list.add(formatter.makeString(new File(fileOrDirectory.getPath() + "\\" + aStr)));
            }
        }
        return list;
    }

    public static void commandLine(String[] line) throws FileNotFoundException {
        FlagArg flagArg = new FlagArg(line);
        StringBuilder result = new StringBuilder();
        File file = new File(flagArg.getInput());
        ArrayList<String> list;
        if ((flagArg.isH()) && (flagArg.isL())) list = makeListing(file, new FileFormat.FileInterfaceLH(file));
        else if (flagArg.isL()) list = makeListing(file, new FileFormat.FileInterfaceL(file));
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
            PrintWriter pw = new PrintWriter(flagArg.getOutput());
            pw.print(result.toString());
            pw.flush();
        }
    }

    public static void main(String[] D) throws Exception {
       try {commandLine(D);}
        catch(Exception ex){
            System.out.println(ex.getMessage());
           }
    }
}