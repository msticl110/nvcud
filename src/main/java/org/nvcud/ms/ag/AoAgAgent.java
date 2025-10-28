package org.nvcud.ms.ag;

import org.nvcud.ms.ag.abs.TransFactory;

import java.lang.instrument.Instrumentation;

public class AoAgAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        TransFactory.cnTrans(inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        TransFactory.cnTrans(inst);
    }


}
