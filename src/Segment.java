import java.util.ArrayList;
import java.util.List;

public class Segment {
    private String segmentNam;
    private int segmentNum;
    private int segmentSize;
    List<Page> pages;

    public Segment(String segmentNam, int segmentSize) {
        this.segmentNam = segmentNam;
        this.segmentSize = segmentSize;
        pages = new ArrayList<>();
    }

    public int getSegmentSize() {
        return segmentSize;
    }

    public void setSegmentSize(int segmentSize) {
        this.segmentSize = segmentSize;
    }

    public String getSegmentNam() {
        return segmentNam;
    }

    public void setSegmentNam(String segmentNam) {
        this.segmentNam = segmentNam;
    }

    public int getSegmentNum() {
        return segmentNum;
    }

    public void setSegmentNum(int segmentNum) {
        this.segmentNum = segmentNum;
    }
}
