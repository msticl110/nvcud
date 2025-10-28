package org.nvcud.ms.ag.tr;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.nvcud.ms.ag.abs.TransAbs;
import org.nvcud.ms.ag.lst.AgbLst;
import org.nvcud.ms.ag.lst.LtyAde;

import java.lang.instrument.Instrumentation;
import java.util.function.Supplier;

public class AgentTransformer extends TransAbs{

    protected final static String strategy = "mz_ad";

    public AgentTransformer() {
        super(strategy);
    }

    public void tranIn(Instrumentation inst, Supplier<String> cn, Supplier<String> mn) {
        new AgentBuilder.Default()
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)
                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
                .with(new AgbLst())
                .type(ElementMatchers.named(cn.get()))
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.visit(Advice.to(LtyAde.class)
                                .on(ElementMatchers.named(mn.get())))
                )
//                .type(ElementMatchers.named("com.mowan.circle.service.lottery.algorithm.GameInfiniteLotteryAlgorithm"))
//                .transform((builder, typeDescription, classLoader, module) ->
//                        builder.visit(Advice.to(LtyAde2.class)
//                                .on(ElementMatchers.named("lottery")))
//                )
                .installOn(inst);
    }

}
