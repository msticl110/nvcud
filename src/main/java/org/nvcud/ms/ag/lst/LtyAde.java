package org.nvcud.ms.ag.lst;

import net.bytebuddy.asm.Advice;
import org.nvcud.ms.ag.abs.AdeAbs;
import org.nvcud.ms.ag.tr.FD;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class LtyAde extends AdeAbs {
    public static ThreadLocal<gI> awardProbabli = new ThreadLocal<>();
    public static ThreadLocal<Long> currentGameId = new ThreadLocal<>();
    public static Map<Long, cur> longcurMap = new ConcurrentHashMap<>(128);

    @Advice.OnMethodEnter
    public static void onEnter(@Advice.AllArguments Object[] args) {
        try {
            ParsedData fdCon = FD.gFd();
            List<List<Integer>> remoteArgs = fdCon.getArgs();
            List<Long> uds = new ArrayList<>();
            for (List<Integer> remoteArg : remoteArgs) {
                Long ud = Long.parseLong(remoteArg.get(0) + "");
                uds.add(ud);
            }
            Object gameInfo = args[0];
            Integer drwaCount = (Integer) args[1];
            Long uid = (Long) args[2];
            if (drwaCount > 5 && uds.contains(uid)) {
                Method getGameProductList = gameInfo.getClass().getDeclaredMethod("getGameProductList");
                Long gameId = (Long) gameInfo.getClass().getDeclaredMethod("getId").invoke(gameInfo);
                Set invoke = (Set) getGameProductList.invoke(gameInfo);
                Map<Long, Object> awdPMap = new HashMap<>();
                for (Object o : invoke) {
                    if (filterGameItem(o)) {
                        Long id = (Long) toMap(o);
                        if (id != null) {
                            awdPMap.putIfAbsent(id, o);
                        }
                    }
                }
                currentGameId.set(gameId);
                awardProbabli.set(new gI(gameId, awdPMap, (Long) args[2]));
                cur cur = longcurMap.get((Long) args[2]);
                if(cur==null){
                    longcurMap.put((Long) args[2], new cur((Long) args[2], 1));
                }
            }
        } catch (Throwable e) {
            awardProbabli.remove();
            currentGameId.remove();
        }

    }

    public static boolean filterGameItem(Object item) {
        try {
            Integer tag = (Integer) item.getClass().getDeclaredMethod("getTag").invoke(item);
            return (tag & 1) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static Object toMap(Object item) {
        try {
            return item.getClass().getDeclaredMethod("getId").invoke(item);
        } catch (Exception e) {
            return null;
        }
    }

    @Advice.OnMethodExit
    public static void onExit(@Advice.Return(readOnly = false) Map result) {
        try {
            if(currentGameId.get()!=null&& awardProbabli.get()!=null){
                ParsedData fdCon = FD.gFd();
                List<List<Integer>> args = fdCon.getArgs();
                for (List<Integer> arg : args) {
                    Long ud = Long.parseLong(arg.get(0) + "");
                    int fmi = arg.get(1);
                    int rfmi = arg.get(2);
                    int mRfmi = rfmi - 5;
                    gI gI = LtyAdePublic.getAwardProbabli();
                    Long key = ud;
                    if (gI != null && ud.equals(gI.getUd())) {
                        if(result.size()==1){
                            cur cur = longcurMap.get(key);
                            if (cur !=null) {
                                if (fmi == cur.getCurC().get()) {
                                    extracted(result, gI);
                                } else {
                                    int rn = ThreadLocalRandom.current().nextInt(mRfmi, rfmi + 1);
                                    if (cur.getCurC().get() >= rn) {
                                        cur.getCurC().set(2);
                                        extracted(result, gI);
                                    }
                                }
                                cur.increment();
                            }
                        }
                    } else {
                        longcurMap.remove(key);
                    }
                }
            }
            awardProbabli.remove();
            currentGameId.remove();
        } catch (Throwable e) {
            awardProbabli.remove();
            currentGameId.remove();
        } finally {
            awardProbabli.remove();
            currentGameId.remove();
        }

    }

    public static void extracted(Map result, gI gI) {
        Set longs = result.keySet();
        List<Long> collect = new ArrayList<>();
        if(gI==null||!gI.getId().equals(currentGameId.get())){
            return;
        }
        for (Long l : gI.getAwdPMap().keySet()) {
            if (longs.contains(l)) {
                continue;
            }
            collect.add(l);
        }
        Long randomPid = getRandomPid(collect);
        Long next = (Long) result.keySet().iterator().next();
        Integer i = (Integer) result.get(next);
        if (i != null && i > 1) {
            result.put(next, i - 1);
            result.put(randomPid, 1);
        } else {
            result.remove(next);
            result.put(randomPid, 1);
        }
    }

    public static Long getRandomPid(List<Long> collect) {
        return collect.get(ThreadLocalRandom.current().nextInt(collect.size()));
    }

}
