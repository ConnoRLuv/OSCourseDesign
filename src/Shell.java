import java.io.BufferedReader;
import java.util.*;

/**
 * 命令接口
 */
public class Shell {
    private static Scanner sc = new Scanner(System.in);

    public static void Page() {
        int choic;
        int cnt;
        List<PCB> processes = new ArrayList<>();
        PCB process;
        System.out.println("********************初始化进程址*******************");
        System.out.print(">请输入进程数：");
        cnt = sc.nextInt();
        for (int i = 0; i < cnt; i++) {
            System.out.print(">输入进程名：");
            String processNam = sc.next();
            System.out.print(">输入进程大小(KB)：");
            int processSize = sc.nextInt() * 1024;
            process = new PCB(processNam, processSize);
            processes.add(process);
            OS.DistributePage(process);
            System.out.println("分配结果：");
            OS.printPageChart(process);
        }
        while (true) {
            System.out.println(">1.地址转换");
            System.out.println(">2.增加进程");
            System.out.println(">0.退出");
            System.out.print(">请选择功能：");
            choic = sc.nextInt();
            switch (choic) {
                case 1:
                    process = null;
                    System.out.print(">输入进程名：");
                    String processName = sc.next();
                    for (PCB pro :
                            processes) {
                        if (pro.getProcessName().equals(processName)) {
                            process = pro;
                            break;
                        }
                    }
                    if (process != null) {


                        System.out.print(">输入十六进制逻辑地址：");

//
                        OS.getPageAddr(process, sc.next());
                    } else
                        System.out.println(">ERROR:此进程未建立");
                    break;
                case 2:
                    System.out.print(">输入进程名：");
                    String processNam = sc.next();
                    System.out.print(">输入进程大小(KB)：");
                    int processSize = sc.nextInt() * 1024;
                    process = new PCB(processNam, processSize);
                    processes.add(process);
                    OS.DistributePage(process);
                    System.out.println("分配结果：");
                    OS.printPageChart(process);
                    break;
                case 0:
                    return;
                default:
                    System.out.println(">ERROR:输入格式错误");
                    break;
            }

        }
    }

    public static void SegmentPage() {
        int choic;
        int cnt;
        List<PCB> processes = new ArrayList<>();
        PCB process = null;
        System.out.println("********************初始化进程址*******************");
        System.out.print("请输入进程数：");
        cnt = sc.nextInt();
        for (int i = 0; i < cnt; i++) {
            System.out.print(">输入进程名：");
            String processNam = sc.next();
            System.out.print(">输入进程大小(KB)：");
            int processSize = sc.nextInt() * 1024;
            System.out.print(">输入进程分段数：");
            int segmentNum = sc.nextInt();
            HashMap<String, Integer> temp = new HashMap<>();
            for (int j = 0; j < segmentNum; j++) {
                System.out.print(">输入段名和段大小(KB)：");
                temp.put(sc.next(), sc.nextInt() * 1024);
            }
            process = new PCB(processNam, processSize, temp);
            processes.add(process);
            OS.DIstributeSegmentPage(process);
            System.out.println("分配结果：");
            OS.printSegmentPageChart(process);
        }

        while (true) {
            System.out.println(">1.地址转换");
            System.out.println(">2.增加进程");
            System.out.println(">0.退出");
            System.out.print(">请选择功能：");
            choic = sc.nextInt();
            switch (choic) {
                case 1:
                    System.out.print(">输入进程名：");
                    String processName = sc.next();
                    process = null;
                    for (PCB pro :
                            processes) {
                        if (pro.getProcessName().equals(processName)) {
                            process = pro;
                            break;
                        }
                    }
                    if (process != null) {
                        List<String> tmp = new ArrayList<>();
                        System.out.print(">输入段名：");
                        tmp.add(sc.next());
                        System.out.print(">输入页号：");
                        tmp.add(sc.next());
                        System.out.print(">输入偏移地址：");
                        tmp.add(sc.next());
//                    MMU.SegmentPage(process, tmp);
                        OS.getSegmentPageAddr(process, tmp);
                    } else
                        System.out.println(">ERROR:此进程未建立");
                    break;
                case 2:
                    System.out.print(">输入进程名：");
                    String processNam = sc.next();
                    System.out.print(">输入进程大小(KB)：");
                    int processSize = sc.nextInt() * 1024;
                    process = new PCB(processNam, processSize);
                    processes.add(process);
                    OS.DistributePage(process);
                    System.out.println("分配结果：");
                    OS.printPageChart(process);
                    break;
                case 0:
                    return;
                default:
                    System.out.println(">ERROR:输入格式错误");
                    break;
            }

        }


    }

    public static void main(String[] args) {

        System.out.print("**********模拟设计内存管理中的地址转换（页式十六进制、段页式）*********" +
                "\n>内存大小为：" + (Memory.getMemorySize() / 1024) + "KB" +
                "\n>页大小为：" + (Memory.getPageSize() / 1024) + "KB" +
                "\n>请选择存储管理方式(1：页式、2：段页式)：");
        int choic = sc.nextInt();
        if (choic == 1) {
            Page();
        } else if (choic == 2) {
            SegmentPage();
        } else {
            System.out.println("输入格式错误");
        }


    }
}
