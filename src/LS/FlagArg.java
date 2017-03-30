package LS;

/**
 * Created by Konstantin on 27.03.2017.
 */
public class FlagArg {
    private String input, output;
    private boolean l=false,h=false,r=false;

    public FlagArg(String[] arg){
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

    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public boolean isL() {
        return l;
    }

    public boolean isH() {
        return h;
    }

    public boolean isR() {
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlagArg)) return false;

        FlagArg flagArg = (FlagArg) o;

        if (isL() != flagArg.isL()) return false;
        if (isH() != flagArg.isH()) return false;
        if (isR() != flagArg.isR()) return false;
        if (!getInput().equals(flagArg.getInput())) return false;
        return getOutput().equals(flagArg.getOutput());
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
