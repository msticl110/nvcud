package org.nvcud.ag;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class AoAgAgent {

    // agentmain 用于动态 attach 时执行
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("[AoAgAgent] Agent attached to running JVM");

        new AgentBuilder.Default()
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION) // 允许重转换
                .type(ElementMatchers.nameStartsWith("com.mowan.circle.service.processor")) // 目标包前缀
                .transform((builder, td, cl, module) ->
                        builder.visit(Advice.to(MethodAdvice.class).on(ElementMatchers.any())))
                .installOn(inst);

        System.out.println("[AoAgAgent] Transformer installed successfully!");
    }
}
