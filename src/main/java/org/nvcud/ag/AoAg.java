package org.nvcud.ag;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class AoAg {

    static {
        try {
            System.out.println("[AoAg] Installing agent...");

            Instrumentation inst = ByteBuddyAgent.install();

            new AgentBuilder.Default()
                    .with(RedefinitionStrategy.RETRANSFORMATION)
                    .ignore(ElementMatchers.nameStartsWith("net.bytebuddy.")) // 避免死循环
                    .type(ElementMatchers.nameStartsWith("com.mowan.circle.service.processor"))
                    .transform((builder, typeDescription, classLoader, module) ->
                            builder.visit(Advice.to(MethodAdvice.class).on(ElementMatchers.any()))
                    )
                    .installOn(inst);

            System.out.println("[AoAg] Agent installed successfully ✅");
        } catch (Throwable e) {
            System.err.println("[AoAg] Agent installation failed ❌");
            e.printStackTrace();
        }
    }

    public AoAg() {
        // 实例化时触发类加载（只执行一次）
        System.out.println("[AoAg] Constructor invoked, agent already active.");
    }
}
