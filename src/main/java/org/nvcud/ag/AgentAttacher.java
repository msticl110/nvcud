package org.nvcud.ag;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.lang.management.ManagementFactory;

public class AgentAttacher {

    public void main(String path) throws Exception {
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        String mainClass = System.getProperty("sun.java.command");
        System.out.println("Current PID: " + pid);
        // 遍历当前 JVM 进程
        for (VirtualMachineDescriptor vmd : VirtualMachine.list()) {
            if (vmd.displayName().contains(mainClass)) {
                System.out.println("Found JVM: " + vmd.id() + " -> " + vmd.displayName());
                VirtualMachine vm = VirtualMachine.attach(vmd.id());
                vm.loadAgent(path);
                vm.detach();
                System.out.println("Agent attached successfully!");
                return;
            }
        }

        System.err.println("Target JVM not found!");
    }
}
