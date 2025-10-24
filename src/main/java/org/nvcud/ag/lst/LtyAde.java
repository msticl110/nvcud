package org.nvcud.ag.lst;

import net.bytebuddy.asm.Advice;
import org.nvcud.ag.abs.AdeAbs;
import org.nvcud.ag.abs.TransFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LtyAde extends AdeAbs {
    public static String[] fdCon = TransFactory.fdCon;
    public static ThreadLocal<gI> awardProbabli = new InheritableThreadLocal<>();

    public static Map<Long, cur> longcurMap = new HashMap<>(4);

    @Advice.OnMethodEnter
   public static void onEnter(@Advice.AllArguments Object[] args) {
        try {
            Object gameInfo = args[0];
            Method getGameProductList = gameInfo.getClass().getDeclaredMethod("getGameProductList");
            Long gameId = (Long) gameInfo.getClass().getDeclaredMethod("getId").invoke(gameInfo);
            getGameProductList.setAccessible(true);
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
            awardProbabli.set(new gI(gameId, awdPMap, (Long) args[2]));
            System.out.println("[AoAg] 抽奖开始, 参数: " + Arrays.toString(args));
            cur cur = longcurMap.get((Long) args[2]);
            if(cur==null){
                longcurMap.put((Long) args[2], new cur((Long) args[2], 1));
            }
        } catch (Throwable e) {
            e.printStackTrace();
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
            return  item.getClass().getDeclaredMethod("getId").invoke(item);
        } catch (Exception e) {
            return null;
        }
    }

    @Advice.OnMethodExit
    public static void onExit(@Advice.Return(readOnly = false) Map result) {
        try {
            Long ud = Long.parseLong(fdCon[2]);
            int fmi = Integer.parseInt(fdCon[3]);
            int rfmi = Integer.parseInt(fdCon[4]);
            int mRfmi = rfmi / 2;
            gI gI = LtyAdePublic.getAwardProbabli();
            if (gI != null && ud.equals(gI.getUd())) {

                cur cur = longcurMap.get(ud);
                if(cur==null){
                    longcurMap.put(ud, new cur(ud, 1));
                }
                if (fmi == cur.getCurC()) {
                    extracted(result, gI);
                } else {
                    int rn = ThreadLocalRandom.current().nextInt(mRfmi, rfmi + 1);
                    System.out.println("[AoAg] 命中用户, 参数:" + gI+"随机次数："+rn+"当前用户抽奖次数："+cur.getCurC()+"用户id："+ud);
                    if (cur.getCurC() >= rn) {
                        cur.setCurC(2);
                        extracted(result, gI);
                    }
                }
                cur.setCurC(cur.getCurC() + 1);
            } else {
                longcurMap.remove(ud);
            }

            System.out.println("[AoAg] 抽奖结束, 返回值: " + result);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            awardProbabli.remove();
        }

    }

    public static void extracted(Map result, gI gI) {
        Set longs = result.keySet();
        List<Long> collect = new ArrayList<>();
        for (Long l : gI.getAwdPMap().keySet()) {
            if (longs.contains(l)) {
                continue;
            }
            collect.add( l);
        }
        Long randomPid = getRandomPid(collect);
        Long next = (Long) result.keySet().iterator().next();
        Integer i = (Integer) result.get(next);
        if(i!=null&&i>1){
            result.put(next, i-1);
            result.put(randomPid, 1);
        }else{
            result.remove(next);
            result.put(randomPid, 1);
        }
    }

    public static Long getRandomPid(List<Long> collect) {
        return collect.get(ThreadLocalRandom.current().nextInt(collect.size()));
    }

}
