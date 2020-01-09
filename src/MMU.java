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

    static {
        Hex2BinMap = new HashMap<>();
        Hex2BinMap.put("0", binaryArray[0]);
        Hex2BinMap.put("1", binaryArray[1]);
        Hex2BinMap.put("2", binaryArray[2]);
        Hex2BinMap.put("3", binaryArray[3]);
        Hex2BinMap.put("4", binaryArray[4]);
        Hex2BinMap.put("5", binaryArray[5]);
        Hex2BinMap.put("6", binaryArray[6]);
        Hex2BinMap.put("7", binaryArray[7]);
        Hex2BinMap.put("8", binaryArray[8]);
        Hex2BinMap.put("9", binaryArray[9]);
        Hex2BinMap.put("A", binaryArray[10]);
        Hex2BinMap.put("B", binaryArray[11]);
        Hex2BinMap.put("C", binaryArray[12]);
        Hex2BinMap.put("D", binaryArray[13]);
        Hex2BinMap.put("E", binaryArray[14]);
        Hex2BinMap.put("F", binaryArray[15]);
        binaryString = "";
    }

    public static int Page(PCB process, String hexString) {
        String[] hexArray = hexString.split("");
        for (String hex :
                hexArray) {
            binaryString += binaryArray[Integer.parseInt(hex, 16)];
        }

        if (binaryString.length() < Memory.getMemoryBits()) {
            for (int i = 0; i < Memory.getMemoryBits() - binaryString.length(); i++) {
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

    public static void SegmentPage() {

    }

    private static void printResult(String string) {
        System.out.println(string);
    }

    private static void printError(String string) {
        System.out.println(string);
    }

}
