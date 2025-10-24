package org.nvcud.ag.lst;

import java.util.List;
import java.util.Map;

public class LtyAdePublic {

    // 提供安全 public 方法访问
    public static gI getAwardProbabli() {
        return LtyAde.awardProbabli.get();
    }

    public static void clearAwardProbabli() {
        LtyAde.awardProbabli.remove();
    }

    public static Map<Long, cur> getLongCurMap() {
        return LtyAde.longcurMap;
    }

    public static Long pickRandomPid(List<Long> collect) {
        return LtyAde.getRandomPid(collect);
    }
}
