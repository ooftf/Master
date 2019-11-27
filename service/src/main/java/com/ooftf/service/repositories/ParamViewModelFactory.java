package com.ooftf.service.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 9/9/2019
 */
public class ParamViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    Object[] param;
    Class[] paramClass;

    /**
     * 第一个参数必须为application
     *
     * @param param
     */
    public ParamViewModelFactory(Object... param) {
        this.param = param;
        paramClass = new Class[param.length];
        for (int i = 0; i < param.length; i++) {
            paramClass[i] = param[i].getClass();
        }
    }

    /**
     * 第一个参数必须为application
     *
     * @param param
     */
    public ParamViewModelFactory(Class[] paramClass, Object[] param) {
        this.param = param;
        this.paramClass = paramClass;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(paramClass).newInstance(param);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return super.create(modelClass);
    }
}
