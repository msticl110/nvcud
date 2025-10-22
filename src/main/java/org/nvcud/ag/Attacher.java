package org.nvcud.ag;

import com.sun.tools.attach.VirtualMachine;
import java.lang.management.ManagementFactory;

public class Attacher {

    public static void attachAgent(String agentPath) {
        try {
            // 获取当前 JVM pid
            String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            System.out.println("[AoAg] Attaching agent to pid=" + pid);

            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent(agentPath);
            vm.detach();

            System.out.println("[AoAg] Agent attached successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
