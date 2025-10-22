package org.nvcud.ag;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

import java.util.Arrays;

public class MethodAdvice {

    /**
     * 方法进入时执行
     */
    @Advice.OnMethodEnter
    public static void onEnter(
            @Advice.Origin String method,
            @Advice.AllArguments Object[] args) {
        System.out.println("[AoAg] Enter: " + method);
        if (args != null && args.length > 0) {
            System.out.println("        args: " + Arrays.toString(args));
        }
    }

    /**
     * 方法退出时执行
     */
    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void onExit(
            @Advice.Origin String method,
            @Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object ret,
            @Advice.Thrown Throwable throwable) {

        if (throwable != null) {
            System.out.println("[AoAg] " + method + " threw exception: " + throwable);
        } else {
            System.out.println("[AoAg] Exit: " + method + " => return: " + ret);

            // ✅ 可选：修改返回值
            if (ret instanceof String) {
                ret = "[Modified by Agent] " + ret;
                System.out.println("[AoAg] Return value modified to: " + ret);
            }
        }
    }
}
