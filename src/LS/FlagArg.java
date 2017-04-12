package LS;

import java.io.File;
import java.util.Objects;

public class FlagArg {
    private String input, output;
    private boolean l = false, h = false, r = false;

    public FlagArg(String[] arg) {
        if (arg.length == 0) throw new IllegalArgumentException("неверная команда");
        for (int i = 0; i < arg.length; i++)
            if (arg[i].length() != 0) {
                switch (arg[i]) {
                    case "-l":
                        l = true;
                        break;
                    case "-h":
                        h = true;
                        break;
                    case "-r":
                        r = true;
                        break;
                    case "-o": {
                        if (arg.length >= i)
                            output = arg[i + 1];
                        else throw new IllegalArgumentException("неверная команда");
                        break;
                    }
                    default:
                        break;
                }
            }
        input = arg[arg.length - 1];
        if (!new File(input).exists())
            throw new NullPointerException("неверный входной путь");
        if (!new File(output).canWrite())
            throw new IllegalArgumentException("неверный выходной путь");
        if (Objects.equals(input, output)) throw new IllegalArgumentException("неверная команда");
    }

    String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    boolean isL() {
        return l;
    }

    boolean isH() {
        return h;
    }

    boolean isR() {
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlagArg)) return false;
        FlagArg flagArg = (FlagArg) o;
        return isL() == flagArg.isL()
                && isH() == flagArg.isH()
                && isR() == flagArg.isR()
                && getInput().equals(flagArg.getInput())
                && getOutput().equals(flagArg.getOutput());
    }

    @Override
    public int hashCode() {
        int result = getInput().hashCode();
        result = 31 * result + getOutput().hashCode();
        result = 31 * result + (isL() ? 1 : 0);
        result = 31 * result + (isH() ? 1 : 0);
        result = 31 * result + (isR() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FlagArg{" +
                "input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", l=" + l +
                ", h=" + h +
                ", r=" + r +
                '}';
    }
}
