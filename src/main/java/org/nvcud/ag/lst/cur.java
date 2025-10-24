package org.nvcud.ag.lst;

import java.util.concurrent.atomic.AtomicInteger;

public class cur {
    private Long ud;
    private AtomicInteger curC;

    public cur(Long ud, int curC) {
        this.ud = ud;
        this.curC = new AtomicInteger(curC);
    }

    public Long getUd() {
        return ud;
    }

    public void setUd(Long ud) {
        this.ud = ud;
    }

    public AtomicInteger getCurC() {
        return curC;
    }

    public void setCurC(AtomicInteger curC) {
        this.curC = curC;
    }
}
