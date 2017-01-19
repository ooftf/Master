package com.master.kit.testcase.annotation;

import android.app.Activity;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Observer;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.Observers;

import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by master on 2016/11/29.
 */

public class AnnotationInject {
    private static final String TGA = "AnnotationInject";

    public static void bindView(final Activity activity){
        Observable.just(activity).flatMap(new Func1<Activity, Observable<Field>>() {
            @Override
            public Observable<Field> call(Activity activity) {
                return Observable.from(activity.getClass().getDeclaredFields());
            }
        }).subscribe(new Action1<Field>() {
            @Override
            public void call(Field field) {
                handleField(field, activity);
            }

        });

    }

    private static void handleField(Field field, Activity activity) {
        if(field.isAnnotationPresent(Myannotation.class)){
            Myannotation annotation = field.getAnnotation(Myannotation.class);
            try {
                field.set(activity,activity.findViewById(annotation.sssssss()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
