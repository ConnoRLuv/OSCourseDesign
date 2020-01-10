import consoletable.ConsoleTable;
import consoletable.table.Cell;

import java.util.ArrayList;
import java.util.List;
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
    public static void DistributePage(PCB process) {
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

    public static void DIstributeSegmentPage(PCB process) {
        Random random = new Random();
        int temp;
        for (Segment segment :
                process.SegmentTable) {
            for (int i = 0; i < segment.pages.size(); i++) {
                do {
                    temp = random.nextInt(Memory.pages.size());
                } while (Memory.pages.get(temp).getPageNo() != -1);
                Memory.pages.get(temp).setPageNo(i);
                Memory.pages.get(temp).setInterruptBit(0);
                Memory.pages.get(temp).setProcessNam(process.getProcessName());
                segment.pages.remove(i);
                segment.pages.add(i, Memory.pages.get(temp));

            }
        }

    }

    public static void printSegmentPageChart(PCB process) {
        System.out.println("--进程" + process.getProcessName() + "段表");
        List<Cell> headerSegment = new ArrayList<>() {{
            add(new Cell("段名称"));
            add(new Cell("页表长度"));
        }};


        List<List<Cell>> bodySegment = new ArrayList<>();
        List<Cell> bodyCellSegment;

        for (Segment segment :
                process.SegmentTable) {
            bodyCellSegment = new ArrayList<>();
            bodyCellSegment.add(new Cell(segment.getSegmentName()));
            bodyCellSegment.add(new Cell(String.valueOf(segment.pages.size())));
            bodySegment.add(bodyCellSegment);


        }

        new ConsoleTable.ConsoleTableBuilder()
                .addHeaders(headerSegment)
                .addRows(bodySegment)
                .build()
                .print();


        List<Cell> headerPage = new ArrayList<>() {{
            add(new Cell("页号"));
            add(new Cell("块号"));
        }};


        for (Segment segment :
                process.SegmentTable) {
            System.out.println("--段" + segment.getSegmentName() + "页表");
            List<List<Cell>> bodyPage = new ArrayList<>();
            List<Cell> bodyCellPage;
            for (Page page :
                    segment.pages) {
                bodyCellPage = new ArrayList<>();
                bodyCellPage.add(new Cell(String.valueOf(page.getPageNo())));
                bodyCellPage.add(new Cell(String.valueOf(page.getPageFrameNum())));
                bodyPage.add(bodyCellPage);

            }

            new ConsoleTable.ConsoleTableBuilder()
                    .addHeaders(headerPage)
                    .addRows(bodyPage)
                    .build()
                    .print();
        }

    }

    public static void printPageChart(PCB process) {

        List<Cell> headerPage = new ArrayList<>() {{
            add(new Cell("页号"));
            add(new Cell("中断位"));
            add(new Cell("块号"));


        }};

        List<List<Cell>> bodyPage = new ArrayList<>();
        List<Cell> bodyCellPage;
        for (Page page :
                process.PageMappingTable) {
            bodyCellPage = new ArrayList<>();
            bodyCellPage.add(new Cell(String.valueOf(page.getPageNo())));
            bodyCellPage.add(new Cell(String.valueOf(page.getInterruptBit())));
            if (page.getPageFrameNum() == -1) {
                bodyCellPage.add(new Cell(""));
            } else
                bodyCellPage.add(new Cell(String.valueOf(page.getPageFrameNum())));

            bodyPage.add(bodyCellPage);
        }

        new ConsoleTable.ConsoleTableBuilder()
                .addHeaders(headerPage)
                .addRows(bodyPage)
                .build()
                .print();
    }

    public static void getSegmentPageAddr(PCB process, List<String> tmp) {
        List<Integer> temp = new ArrayList<>();
        String segmentName = tmp.get(0);

        for (int i = 0; i < process.SegmentTable.size(); i++) {
            if (process.SegmentTable.get(i).getSegmentName().equals(segmentName))
                temp.add(0, i);
        }
        if (temp.size() == 0)
            temp.add(-1);
        temp.add(Integer.parseInt(tmp.get(1)));
        temp.add(Integer.parseInt(tmp.get(2)));

        MMU.SegmentPage(process, temp);

    }

    public static void getPageAddr(PCB process, String hexAddr) {
        MMU.Page(process, hexAddr);
    }


}
