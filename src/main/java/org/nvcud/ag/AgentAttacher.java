package org.nvcud.ag;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.lang.management.ManagementFactory;

public class AgentAttacher {

    public void main(String path) throws Exception {
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        String mainClass = System.getProperty("sun.java.command");
        for (VirtualMachineDescriptor vmd : VirtualMachine.list()) {
            if (vmd.displayName().contains(mainClass)) {
                VirtualMachine vm = VirtualMachine.attach(vmd.id());
                vm.loadAgent(path);
                vm.detach();
                return;
            }
        }
    }
}
