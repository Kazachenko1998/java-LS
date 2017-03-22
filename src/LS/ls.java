package LS;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
            return result + (size) + "Bait";
    }

    private static String sizeFile(File file) {
        long size = file.length();
        return "" + size;
    }

    private static String data(File file) {
        Date date = new Date(file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return "  последние изменение " + sdf.format(date) + "  ";
    }

    private static String[] split_flag(String input) {
        String[] result = input.split(" ");
        return split_flag(result);
    }
    private static String[] split_flag(String[] result) {
        String[] result2 = new String[]{"0", "0", "0", null, null};
        for (int i = 1; i < result.length; i++)
            if (result[i].length() != 0) {
                switch (result[i]) {
                    case "-l":
                        result2[0] = "1";
                        break;
                    case "-h":
                        result2[1] = "1";
                        break;
                    case "-r":
                        result2[2] = "1";
                        break;
                    case "-o": {
                        if (result.length >= i)
                            result2[3] = result[i+1];
                        break;
                    }
                    default:
                        break;
                }
            }
        result2[4] = result[result.length-1];
        return result2;
    }

    public static String commandLine(String line) {
        if (line.length() < 2) throw new IllegalArgumentException("неверная команда");
        if (!(((line.charAt(0) == 'l') || (line.charAt(0) == 'L')) && ((line.charAt(1) == 's') || (line.charAt(1) == 'S'))))
            throw new IllegalArgumentException("неверная команда");
        String result = new String();
        File file = new File(split_flag(line)[4]);
        String[] str = file.list();
        if (file.isFile()) str = new String[]{split_flag(line)[4]};
        String[] flags = split_flag(line);
        ArrayList<String> list = new ArrayList<>();
        int size = 0;
        try {
            for (int i = 0; i < str.length; i++) {
                if (str[i].length() > size) size = str[i].length();
            }
        } catch (NullPointerException ex) {
            throw new NullPointerException("неверный входной путь");
        }
        if (file.isDirectory()) for (int i = 0; i < str.length; i++) {
            String space = new String();
            for (int j = str[i].length(); j < size + 1; j++)
                space += " ";
            if (flags[0] == "0") list.add(str[i]);
            if ((flags[0] == "1") && (flags[1] == "0")) list.add(
                    file.getPath() + "\\" + str[i] + space + " " +
                            accessBite(new File(file.getPath() + "\\" + str[i])) + " "
                            + (new File(file.getPath() + "\\" + str[i])).lastModified() + " "
                            + sizeFile(new File(file.getPath() + "\\" + str[i])));
            if ((flags[0] == "1") && (flags[1] == "1")) list.add(
                    str[i] + space + " " +
                            accessHuman(new File(file.getPath() + "\\" + str[i])) + " "
                            + data(new File(file.getPath() + "\\" + str[i])) + " "
                            + sizeFileHuman(new File(file.getPath() + "\\" + str[i])));
        }
        if (file.isFile()) for (int i = 0; i < str.length; i++) {
            String space = new String();
            for (int j = str[i].length(); j < size + 1; j++)
                space += " ";
            if (flags[0] == "0") list.add(str[i]);
            if ((flags[0] == "1") && (flags[1] == "0")) list.add(
                    file.getPath() + space + " " +
                            accessBite(file) + " "
                            + file.lastModified() + " "
                            + sizeFile(file));
            if ((flags[0] == "1") && (flags[1] == "1")) list.add(
                    str[i] + space + " " +
                            accessHuman(file) + " "
                            + data(file) + " "
                            + sizeFileHuman(file));
        }
        if (flags[2] == "1") for (int i = list.size() - 1; i >= 0; i--)
            result += list.get(i) + "\n";
        else for (int i = 0; i < list.size(); i++)
            result += list.get(i) + "\n";
        if (flags[3] == null)
            return result;
        else {
            try (
                    FileWriter writer = new FileWriter(flags[3], false)) {
                writer.write(result);
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return "";
    }


    private static String commandLine(String[] line) {
        if (line.length == 0) throw new IllegalArgumentException("неверная команда");
        if (!(line[0].equals("ls")))
            throw new IllegalArgumentException("неверная команда");
        String result = new String();
        File file = new File(split_flag(line)[4]);
        String[] str = file.list();
        if (file.isFile()) str = new String[]{split_flag(line)[4]};
        String[] flags = split_flag(line);
        ArrayList<String> list = new ArrayList<>();
        int size = 0;
        try {
            for (int i = 0; i < str.length; i++) {
                if (str[i].length() > size) size = str[i].length();
            }
        } catch (NullPointerException ex) {
            throw new NullPointerException("неверный входной путь");
        }
        if (file.isDirectory()) for (int i = 0; i < str.length; i++) {
            String space = new String();
            for (int j = str[i].length(); j < size + 1; j++)
                space += " ";
            if (flags[0] == "0") list.add(str[i]);
            if ((flags[0] == "1") && (flags[1] == "0")) list.add(
                    file.getPath() + "\\" + str[i] + space + " " +
                            accessBite(new File(file.getPath() + "\\" + str[i])) + " "
                            + (new File(file.getPath() + "\\" + str[i])).lastModified() + " "
                            + sizeFile(new File(file.getPath() + "\\" + str[i])));
            if ((flags[0] == "1") && (flags[1] == "1")) list.add(
                    str[i] + space + " " +
                            accessHuman(new File(file.getPath() + "\\" + str[i])) + " "
                            + data(new File(file.getPath() + "\\" + str[i])) + " "
                            + sizeFileHuman(new File(file.getPath() + "\\" + str[i])));
        }
        if (file.isFile()) for (int i = 0; i < str.length; i++) {
            String space = new String();
            for (int j = str[i].length(); j < size + 1; j++)
                space += " ";
            if (flags[0] == "0") list.add(str[i]);
            if ((flags[0] == "1") && (flags[1] == "0")) list.add(
                    file.getPath() + space + " " +
                            accessBite(file) + " "
                            + file.lastModified() + " "
                            + sizeFile(file));
            if ((flags[0] == "1") && (flags[1] == "1")) list.add(
                    str[i] + space + " " +
                            accessHuman(file) + " "
                            + data(file) + " "
                            + sizeFileHuman(file));
        }
        if (flags[2] == "1") for (int i = list.size() - 1; i >= 0; i--)
            result += list.get(i) + "\n";
        else for (int i = 0; i < list.size(); i++)
            result += list.get(i) + "\n";
        if (flags[3] == null)
            return result;
        else {
            try (
                    FileWriter writer = new FileWriter(flags[3], false)) {
                writer.write(result);
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return "";
    }

    public static void main(String[] D) throws Exception {
//     BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
//       String input = reader.readLine();
//        String[] input2 = D;
//        for (int i = 0;i<D.length;i++)
//            System.out.println(D[i]);
        String input = "ls -l -h -o help C:\\";
        System.out.print(commandLine("ls -o __f testIn\\Ace_Stream_Media_3.1.2.exe"));
        System.out.print(commandLine("ls -l -o l_f testIn\\Ace_Stream_Media_3.1.2.exe"));
        System.out.print(commandLine("ls -l -h -o lhf testIn\\Ace_Stream_Media_3.1.2.exe"));
        System.out.print(commandLine("ls  -o __d testIn"));
        System.out.print(commandLine("ls -l  -o l_d testIn"));
        System.out.print(commandLine("ls -l -h -o lhd testIn"));
        //       System.out.print(commandLine(D));

    }
}
