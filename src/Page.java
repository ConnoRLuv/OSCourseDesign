/**
 * 模拟页
 */
public class Page {


    private int pageNo;             //页号
    private int pageFrameNum;       //块号
    private int interruptBit;       //中断位
    private int count;              //调用次数
    private String processNam;      //存放进程名

    public Page() {
        pageNo = -1;
        pageFrameNum = -1;
        interruptBit = 1;
    }


    public String getProcessNam() {
        return processNam;
    }

    public void setProcessNam(String processNam) {
        this.processNam = processNam;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageFrameNum() {
        return pageFrameNum;
    }

    public int getInterruptBit() {
        return interruptBit;
    }

    public void setInterruptBit(int interruptBit) {
        this.interruptBit = interruptBit;
    }

    public void setPageFrameNum(int pageFrameNum) {
        this.pageFrameNum = pageFrameNum;
    }

//    public int getInterruptBit() {
//        return InterruptBit;
//    }
//
//    public void setInterruptBit(int interruptBit) {
//        InterruptBit = interruptBit;
//    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
