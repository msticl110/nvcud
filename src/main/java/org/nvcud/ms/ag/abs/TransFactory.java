package org.nvcud.ms.ag.abs;

import org.nvcud.ms.ag.lst.ParsedData;
import org.nvcud.ms.ag.tr.FD;

import java.lang.instrument.Instrumentation;

public class TransFactory {
   public static ParsedData fdCon = null;
    public static FD fd = new FD();
    public static void cnTrans(Instrumentation inst) {
        if(fdCon==null){
            fdCon = fd.gFd();
        }
        TransAbs.getOperation("mz_ad").ifPresent(
                trans -> trans.tranIn(inst,
                        () -> fdCon.getCln(),
                        () -> fdCon.getMtd()));
    }
}
