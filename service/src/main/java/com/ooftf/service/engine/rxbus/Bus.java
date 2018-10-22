package com.ooftf.service.engine.rxbus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.subjects.PublishSubject;

public class Bus {

    /*List<PublishSubject> publishSubjects = new ;

    Observable register(String event) {
        return publishSubject
                .filter(new AppendOnlyLinkedArrayList.NonThrowingPredicate() {
                    @Override
                    public boolean test(Object o) {
                        return false;
                    }
                });
    }

    Observable unRegister(String event) {
        return publishSubject.filter(new AppendOnlyLinkedArrayList.NonThrowingPredicate() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        });
    }

    void post(String event) {
        publishSubject.
        publishSubject.onNext("NULL");
    }

    void post(String event, Object object) {
        publishSubject.onNext(object);
    }*/
}
