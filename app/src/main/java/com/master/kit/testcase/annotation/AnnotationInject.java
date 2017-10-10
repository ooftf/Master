package com.master.kit.testcase.annotation;

import android.app.Activity;

import java.lang.reflect.Field;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by master on 2016/11/29.
 */

public class AnnotationInject {
    private static final String TGA = "AnnotationInject";

    public static void bindView(final Activity activity){
        Observable.just(activity).flatMap(new Function<Activity, ObservableSource<Field>>() {
            @Override
            public ObservableSource<Field> apply(Activity activity) throws Exception {
                return Observable.fromArray(activity.getClass().getDeclaredFields());
            }

        }).subscribe(new Consumer<Field>() {
            @Override
            public void accept(Field field) throws Exception {
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
