package org.nvcud.ms.ag;

import com.sun.tools.attach.VirtualMachine;

public class AgentAttacher {
    public void attach(String path,String pid)  {
        try {
            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent(path);
            vm.detach();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
