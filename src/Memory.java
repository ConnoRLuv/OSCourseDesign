import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Memory {

    static List<Page> pages;
    private static int memorySize;
    private static int pageSize;
    private static int pageNum;
    private static int memoryBits;
    private static int pageBits;

    static {

        try {
            InputStream is = new FileInputStream("src/memory");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            int memorySize = 0, pageSize = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                memorySize = Integer.parseInt(line.split(" ")[0]);
                pageSize = Integer.parseInt(line.split(" ")[1]);
            }
            new Memory(memorySize, pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public Memory(int memorySize, int pageSize) {
        setMemorySize(memorySize);
        setPageSize(pageSize);
        pages = new ArrayList<>();
        int tmpPageNum;
        if (memorySize % pageSize == 0) {
            pageNum = memorySize / pageSize;
        } else
            pageNum = memorySize / pageSize + 1;

        for (int i = 0; i < pageNum; i++) {
            Page page = new Page();
            page.setPageFrameNum(i);
            pages.add(page);
        }

        memoryBits = Integer.toBinaryString(memorySize - 1).length();
        pageBits = memoryBits - Integer.toBinaryString(pageSize - 1).length();
    }

    public static int getPageBits() {
        return pageBits;
    }

    public static int getMemoryBits() {
        return memoryBits;
    }

    public static int getPageSize() {
        return pageSize;
    }

    public static void setPageSize(int pageSize) {
        Memory.pageSize = pageSize;
    }

    public static int getMemorySize() {
        return memorySize;
    }

    public static void setMemorySize(int memorySize) {
        Memory.memorySize = memorySize;
    }
}
