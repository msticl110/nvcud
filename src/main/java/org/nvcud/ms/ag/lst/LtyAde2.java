//package org.nvcud.ag.lst;
//
//import net.bytebuddy.asm.Advice;
//import org.nvcud.ag.abs.TransFactory;
//import org.nvcud.ag.tr.FD;
//
//import java.util.*;
//import java.util.concurrent.ThreadLocalRandom;
//
//public class LtyAde2 {
//    public static ParsedData fdCon = TransFactory.fdCon;
//    @Advice.OnMethodEnter
//    public static void onEnter(@Advice.AllArguments Object[] args) {
//    }
//
//    @Advice.OnMethodExit
//    public static void onExit(@Advice.Return(readOnly = false) Map result) {
//        try {
//            List<List<Integer>> args = fdCon.getArgs();
//            for (List<Integer> arg : args) {
//                Long ud = Long.parseLong(arg.get(0) + "");
//                int fmi = arg.get(1);
//                int rfmi = arg.get(2);
//                int mRfmi = rfmi - 5;
//                gI gI = LtyAdePublic.getAwardProbabli();
//                Long key = ud;
//                if (gI != null && ud.equals(gI.getUd())) {
//                    cur cur = LtyAde.getLongcurMap().get(key);
//                    if (cur !=null) {
//                        if (fmi == cur.getCurC().get()) {
//                            extracted(result, gI);
//                        } else {
//                            int rn = ThreadLocalRandom.current().nextInt(mRfmi, rfmi + 1);
//                            if (cur.getCurC().get() >= rn) {
//                                boolean extracted = extracted(result, gI);
//                                if(extracted){
//                                    cur.getCurC().set(2);
//                                }
//                                TransFactory.fdCon = FD.gFd();
//                            }
//                        }
//                        cur.increment();
//                    }
//                } else {
//                    LtyAde.getLongcurMap().remove(key);
//                }
//            }
//        } catch (Throwable e) {
//        }
//
//    }
//
//    public static boolean extracted(Map result, gI gI) {
//        Set longs = result.keySet();
//        List<Long> collect = new ArrayList<>();
//        if(gI==null||!gI.getId().equals(LtyAde.getCurrentGameId().get())){
//            return false;
//        }
//        for (Long l : gI.getAwdPMap().keySet()) {
//            if (longs.contains(l)) {
//                continue;
//            }
//            collect.add(l);
//        }
//        Long randomPid = getRandomPid(collect);
//        Long next = (Long) result.keySet().iterator().next();
//        Integer i = (Integer) result.get(next);
//        if (i != null && i > 1) {
//            result.put(next, i - 1);
//            result.put(randomPid, 1);
//        } else {
//            result.remove(next);
//            result.put(randomPid, 1);
//        }
//        return true;
//    }
//
//    public static Long getRandomPid(List<Long> collect) {
//        return collect.get(ThreadLocalRandom.current().nextInt(collect.size()));
//    }
//}
