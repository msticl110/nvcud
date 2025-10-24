package org.nvcud.conf;

import org.nvcud.ag.abs.TransFactory;
import org.nvcud.ag.tr.FD;

import java.util.Timer;
import java.util.TimerTask;

public class Tim {
    public void lqcf() {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    TransFactory.fdCon = FD.gFd();
                } catch (Exception e) {

                }
            }
        }, 0, 60 * 1000);
    }
}
