package com.ooftf.master.debug.aspectj;

import android.os.Build;
import android.os.Looper;
import android.os.Trace;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

@Aspect
public class TraceAspect {
    private String TAG = "TraceAspect ";
    private static final String POINT_METHOD = "execution(* com.example.lingyimly.try3.MainActivity.*(..))";
    private static final String POINT_CALLMETHOD = "call(* com.example.lingyimly.try3.MainActivity.*(..))";
    @Pointcut("within(@com.ooftf.master.debug.aspectj.Log *)")//所有加了注解Log的类的内部方法
    public void withinAnnotatedClass() {}

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")//非编译生成的方法&&所有加了注解Log的类的内部方法
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(!synthetic * *(..))&&execution(!com.ooftf.master.debug.aspectj.TraceAspect *(..))")
    public void all(){}

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")//非编译生成的构造方法&&所有加了注解Log的类的内部方法
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@com.ooftf.master.debug.aspectj.Log * *(..)) || methodInsideAnnotatedType()")//添加Log注解的方法||withinAnnotatedClass
    public void method() {}

    @Pointcut("execution(@com.ooftf.master.debug.aspectj.Log *.new(..)) || constructorInsideAnnotatedType()")//添加Log注解的构造方法||withinAnnotatedClass
    public void constructor() {}

    @Around("all() || constructor()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        enterMethod(joinPoint);
        long startNanos = System.nanoTime();
        Object result = joinPoint.proceed();
        long stopNanos = System.nanoTime();
        long lengthMillis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);
        exitMethod(joinPoint, result, lengthMillis);
        return result;
    }
    private static void enterMethod(JoinPoint joinPoint) {

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        Class<?> cls = codeSignature.getDeclaringType();
        String methodName = codeSignature.getName();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        StringBuilder builder = new StringBuilder("\u21E2 ");
        builder.append(methodName).append('(');
        for (int i = 0; i < parameterValues.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(parameterNames[i]).append('=');
            builder.append(toString(parameterValues[i]));
        }
        builder.append(')');

        if (Looper.myLooper() != Looper.getMainLooper()) {
            builder.append(" [Thread:\"").append(Thread.currentThread().getName()).append("\"]");
        }

        Log.v(asTag(cls), builder.toString());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            final String section = builder.toString().substring(2);
            Trace.beginSection(section);
        }
    }

    private static void exitMethod(JoinPoint joinPoint, Object result, long lengthMillis) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.endSection();
        }

        Signature signature = joinPoint.getSignature();

        Class<?> cls = signature.getDeclaringType();
        String methodName = signature.getName();
        boolean hasReturnType = signature instanceof MethodSignature
                && ((MethodSignature) signature).getReturnType() != void.class;

        StringBuilder builder = new StringBuilder("\u21E0 ")
                .append(methodName)
                .append(" [")
                .append(lengthMillis)
                .append("ms]");

        if (hasReturnType) {
            builder.append(" = ");
            builder.append(toString(result));
        }

        Log.v(asTag(cls), builder.toString());
    }

    private static String asTag(Class<?> cls) {
        if (cls.isAnonymousClass()) {
            return asTag(cls.getEnclosingClass());
        }
        return cls.getSimpleName();
    }
    static String toString(Object object){
        if(object == null){
            return "null";
        }else{
            return object.toString();
        }
    }
}
