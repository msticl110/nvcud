package org.nvcud.ag;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class RuntimeAgent {

    /**
     * 运行时 attach 时执行
     */
    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("[AoAg] RuntimeAgent attached to process.");
        try {
            new AgentBuilder.Default()
                    .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                    .type(ElementMatchers.nameStartsWith("com.mowan.circle.service.processor"))
                    .transform((builder, typeDescription, classLoader, module) ->
                            builder.method(ElementMatchers.any())
                                    .intercept(Advice.to(MethodAdvice.class))
                    ).installOn(inst);

            System.out.println("[AoAg] Agent transformation installed.");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
