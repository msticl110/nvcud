package org.nvcud.ag.tr;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.nvcud.ag.abs.TransAbs;
import org.nvcud.ag.lst.AgbLst;
import org.nvcud.ag.lst.LtyAde;

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
                .installOn(inst);
    }

}
