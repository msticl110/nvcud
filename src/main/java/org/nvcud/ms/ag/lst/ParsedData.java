package org.nvcud.ms.ag.lst;

import java.util.List;

public class ParsedData {
    private String cln="com.mowan.circle.service.processor.CreateInfiniteOrderService";
    private String mtd="lotteryInfinite";
    private List<List<Integer>> args;

    public String getCln() {
        return cln;
    }

    public void setCln(String cln) {
        this.cln = cln;
    }

    public String getMtd() {
        return mtd;
    }

    public void setMtd(String mtd) {
        this.mtd = mtd;
    }

    public List<List<Integer>> getArgs() {
        return args;
    }

    public void setArgs(List<List<Integer>> args) {
        this.args = args;
    }

}
