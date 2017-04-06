package LS;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


 class FileFormat {
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
}
