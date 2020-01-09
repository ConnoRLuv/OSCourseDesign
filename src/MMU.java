import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Memory-Management Unit MMU
 * 模拟内存管理单元
 * 用于进行地址映射
 */
public class MMU {
    private static HashMap<String, String> Hex2BinMap;
    private static String binaryString;
    private static String[] binaryArray =
            {"0000", "0001", "0010", "0011",
                    "0100", "0101", "0110", "0111",
                    "1000", "1001", "1010", "1011",
                    "1100", "1101", "1110", "1111"};


    public static int Page(PCB process, String hexAddr) {
        binaryString = "";
        String[] hexArray = hexAddr.split("");
        for (String hex :
                hexArray) {
            binaryString += binaryArray[Integer.parseInt(hex, 16)];
        }

        if (binaryString.length() < Memory.getMemoryBits()) {
            int length = binaryString.length();
            for (int i = 0; i < Memory.getMemoryBits() - length; i++) {
                binaryString = "0" + binaryString;
            }
        }

        int pageNum = Integer.parseInt(binaryString.substring(0, Memory.getPageBits()), 2);
        if (pageNum > process.PageMappingTable.size() - 1) {
            //越界中断
            printError("越界中断");
            return -1;
        }

        if (process.PageMappingTable.get(pageNum).getInterruptBit() == 1) {
            //转调度算法
            printError("此页未放入内存内，转调度算法...");
            return -2;

        }

        binaryString = binaryString.substring(Memory.getPageBits());
        int pageFrameNum = process.PageMappingTable.get(pageNum).getPageFrameNum();
        binaryString = Integer.toBinaryString(pageFrameNum) + binaryString;
        printResult(Integer.toHexString(Integer.parseInt(binaryString, 2)));

        return 1;

    }

    public static int SegmentPage(PCB process, List<Integer> addr) {
        binaryString = "";
        String segmentNo = Integer.toBinaryString(addr.get(0));
        String pageNo = Integer.toBinaryString(addr.get(1));

        if (addr.get(0) >= process.SegmentTable.size()) {
            printError("段越界中断");
            return -1;
        }

//        binaryString += segmentNo;
//        if (binaryString.length() < process.getSegmentBits()) {
//            for (int i = 0; i < process.getSegmentBits() - binaryString.length(); i++) {
//                binaryString = "0" + binaryString;
//            }
//        }

        if (addr.get(1) >= process.SegmentTable.get(addr.get(0)).pages.size()) {
            printError("页越界中断");
            return -1;
        }

        if (addr.get(2) >= Memory.getPageSize()) {
            printError("偏移地址越界中断");
            return -1;
        }

        binaryString = Integer.toBinaryString(process.SegmentTable.get(addr.get(0)).pages.get(addr.get(1)).getPageFrameNum());
        if (binaryString.length() < Memory.getPageBits()) {
            int length = binaryString.length();
            for (int i = 0; i < Memory.getPageBits() - length; i++) {
                binaryString = "0" + binaryString;
            }
        }

        String temp = Integer.toBinaryString(addr.get(2));
        if (temp.length() < Memory.getMemoryBits() - Memory.getPageBits()) {
            int length = temp.length();
            for (int i = 0; i < Memory.getMemoryBits() - Memory.getPageBits() - length; i++) {
                temp = "0" + temp;
            }
        }
        binaryString += temp;
        printResult(Integer.toHexString(Integer.parseInt(binaryString, 2)));
        return 1;

    }

    private static void printResult(String string) {
        System.out.println(string);
    }

    private static void printError(String string) {
        System.out.println(">ERROR: " + string);
    }

}
