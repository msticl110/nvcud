package org.nvcud.ms.ag.lst;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;

public class AgbLst implements AgentBuilder.Listener{
    @Override
    public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

    }

    @Override
    public void onTransformation(net.bytebuddy.description.type.TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, net.bytebuddy.dynamic.DynamicType dynamicType) {

    }

    @Override public void onIgnored(net.bytebuddy.description.type.TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {}
    @Override
    public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {

    }
    @Override public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {}

}
