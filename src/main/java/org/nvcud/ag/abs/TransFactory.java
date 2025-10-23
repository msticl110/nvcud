package org.nvcud.ag.abs;

import java.lang.instrument.Instrumentation;

public class TransFactory {
    public static void cnTrans(Instrumentation inst) {
        TransAbs.getOperation("mz_ad").ifPresent(
                trans -> trans.tranIn(inst,
                        () -> "com.mowan.circle.service.processor.CreateInfiniteOrderService",
                        () -> "lotteryInfinite"));
    }
}
