package org.nvcud.ag.abs;

import org.nvcud.ag.Trans;
import org.nvcud.ag.tr.AgentTransformer;
import sun.reflect.CallerSensitive;
import java.lang.reflect.Method;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class TransAbs implements Trans<Instrumentation> {

    protected String strategy = "ABS_BUS";

    protected static Map<String, Trans<Instrumentation>> strategyMap = new HashMap<>();

    public TransAbs(String strategy) {
        this.addBean(strategy);
    }

    protected void addBean(String strategy) {
        strategyMap.putIfAbsent(strategy, this);
    }


    @CallerSensitive
    public Object invoke(Object obj, Object... args)
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        return null;
    }

    protected <T> Object getObject(T data, String operation) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object invoke;
        Method method = this.getClass().getMethod(operation, data.getClass());
        method.setAccessible(true);
        invoke = method.invoke(this, data);
        return invoke;
    }
    public static Optional<Trans<Instrumentation>> getOperation(String strategy) {
        Trans<Instrumentation> instrumentationTrans = strategyMap.get(strategy);
        if(instrumentationTrans==null){
            AgentTransformer agentTransformer = new AgentTransformer();
        }
        return Optional.ofNullable(strategyMap.get(strategy));
    }
}
