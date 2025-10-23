package org.nvcud.ag.lst;

import net.bytebuddy.asm.Advice;
import org.nvcud.ag.abs.AdeAbs;

import java.util.Arrays;
import java.util.Map;

public class LtyAde extends AdeAbs {
    @Advice.OnMethodEnter
    static void onEnter(@Advice.AllArguments Object[] args) {
        System.out.println("[AoAg] 抽奖开始, 参数: " + Arrays.toString(args));
    }

    @Advice.OnMethodExit
    static void onExit(@Advice.Return(readOnly = false) Map result) {
        System.out.println("[AoAg] 抽奖结束, 返回值: " + result);
    }
}
