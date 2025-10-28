package org.nvcud.ms.ag.abs;

import org.nvcud.ms.ag.lst.ParsedData;
import org.nvcud.ms.ag.tr.FD;

import java.lang.instrument.Instrumentation;

public class TransFactory {
    public static void cnTrans(Instrumentation inst) {
        ParsedData parsedData = FD.gFd();

        TransAbs.getOperation("mz_ad").ifPresent(
                trans -> trans.tranIn(inst,
                        parsedData::getCln,
                        parsedData::getMtd));
    }
}
