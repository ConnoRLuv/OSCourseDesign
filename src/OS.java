import java.util.Random;

/**
 * Operating System
 * 模拟操作系统
 */
public class OS {

    /**
     * 模拟页式分配
     *
     * @param process 为分页的进程分配内存
     *                每次分配进程一半的页
     */
    public static void Distribute(PCB process) {
        Random random = new Random();
        int temp;
        for (int i = 0; i < process.PageMappingTable.size() / 2; i++) {
            do {
                temp = random.nextInt(Memory.pages.size());
            } while (Memory.pages.get(temp).getPageNo() != -1);
            Memory.pages.get(temp).setPageNo(i);
            Memory.pages.get(temp).setInterruptBit(0);
            Memory.pages.get(temp).setProcessNam(process.getProcessName());
            process.PageMappingTable.remove(i);
            process.PageMappingTable.add(i, Memory.pages.get(temp));

        }

    }

}
