package org.nvcud.ag.lst;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;

public class AgbLst implements AgentBuilder.Listener{
    @Override
    public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
        if (typeName.contains("CreateInfiniteOrderService")) {
            System.out.println("[AoAg] 发现类：" + typeName + " (loaded=" + loaded + ")");
        }
    }

    @Override
    public void onTransformation(net.bytebuddy.description.type.TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, net.bytebuddy.dynamic.DynamicType dynamicType) {
        System.out.println("[AoAg] ✅ 成功增强类：" + typeDescription.getName());
    }

    @Override public void onIgnored(net.bytebuddy.description.type.TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {}
    @Override
    public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
        System.err.println("[AoAg] ❌ 增强出错: " + typeName);
        throwable.printStackTrace();
    }
    @Override public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {}

}
