package org.nvcud.ms.ag.lst;

import java.util.Map;

public class gI {
    private Long id;
    private  Long ud;
    private Map<Long, Object> awdPMap;

    public gI(Long id, Map<Long, Object> awdPMap, Long ud) {
        this.id = id;
        this.awdPMap = awdPMap;
        this.ud = ud;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUd() {
        return ud;
    }

    public void setUd(Long ud) {
        this.ud = ud;
    }

    public Map<Long, Object> getAwdPMap() {
        return awdPMap;
    }

    public void setAwdPMap(Map<Long, Object> awdPMap) {
        this.awdPMap = awdPMap;
    }

}
