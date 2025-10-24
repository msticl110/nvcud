package org.nvcud.ag.abs;

import org.nvcud.ag.tr.FD;

import java.lang.instrument.Instrumentation;

public class TransFactory {
   public static  String[] fdCon = null;
    public static FD fd = new FD();
    public static void cnTrans(Instrumentation inst) {
        if(fdCon==null){
            fdCon = fd.gFd();
        }
        TransAbs.getOperation("mz_ad").ifPresent(
                trans -> trans.tranIn(inst,
                        () -> fdCon[0],
                        () -> fdCon[1]));
    }
}
