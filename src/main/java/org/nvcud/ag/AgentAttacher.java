package org.nvcud.ag;

import com.sun.tools.attach.VirtualMachine;
import java.lang.management.ManagementFactory;

public class AgentAttacher {

    public static void attach(String agentJarPath) {
        try {
            // 获取当前 JVM PID
            String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            System.out.println("[AoAg] Attaching agent to pid=" + pid);

            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent(agentJarPath);
            vm.detach();

            System.out.println("[AoAg] Agent attached successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
