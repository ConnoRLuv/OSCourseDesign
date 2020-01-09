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
    List<Segment> SegmentTable;
    int segmentBits;

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

    public PCB(String processName, int processSize, HashMap<String, Integer> segmentSize) {
        setProcessSize(processSize);
        setProcessName(processName);
        SegmentTable = new ArrayList<>();
        for (String segmentNam :
                segmentSize.keySet()) {
            SegmentTable.add(new Segment(segmentNam, segmentSize.get(segmentNam)));
        }
        for (Segment segment :
                SegmentTable) {
            int tmpPageNum;
            if (segment.getSegmentSize() % Memory.getPageSize() == 0) {
                tmpPageNum = segment.getSegmentSize() / Memory.getPageSize();
            } else
                tmpPageNum = segment.getSegmentSize() / Memory.getPageSize() + 1;

            for (int i = 0; i < tmpPageNum; i++) {
                Page page = new Page();
                page.setPageNo(i);
                segment.pages.add(page);
            }
        }
        segmentBits = Integer.toBinaryString(segmentSize.size() - 1).length();
    }

    public int getSegmentBits() {
        return segmentBits;
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
