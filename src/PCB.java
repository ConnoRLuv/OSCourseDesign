import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Process Control Block
 * 模拟进程控制块
 */
public class PCB {
    private int processSize;
    private String processName;
    List<Page> PageMappingTable;
    List<Page> TLB;

    public PCB(String processName, int size) {
        setProcessSize(size);
        setProcessName(processName);
        PageMappingTable = new ArrayList<>();
        TLB = new ArrayList<>();

        int tmpPageNum;
        if (processSize % Memory.getPageSize() == 0) {
            tmpPageNum = processSize / Memory.getPageSize();
        } else
            tmpPageNum = processSize / Memory.getPageSize() + 1;

        for (int i = 0; i < tmpPageNum; i++) {
            Page page = new Page();
            page.setPageNo(i);
            PageMappingTable.add(page);
        }
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getProcessSize() {
        return processSize;
    }

    public void setProcessSize(int processSize) {
        this.processSize = processSize;
    }

    @Override
    public String toString() {
        return getProcessName();
    }
}
