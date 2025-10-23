package org.nvcud.ag;

import org.nvcud.ag.abs.TransFactory;
import org.nvcud.ag.tr.AgentTransformer;

import java.lang.instrument.Instrumentation;

public class AoAgAgent {
    public void mow(){

    }

    public void an(){

    }
    public static void premain(String agentArgs, Instrumentation inst) {

        TransFactory.cnTrans(inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        TransFactory.cnTrans(inst);
    }


}
