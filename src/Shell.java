import java.util.Scanner;

/**
 * 命令接口
 */
public class Shell {

    public static void main(String[] args) {

        PCB process = new PCB("A", 12288);
        OS.Distribute(process);
        MMU.Page(process, "0123");
    }
}
