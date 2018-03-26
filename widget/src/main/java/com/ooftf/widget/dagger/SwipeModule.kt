package com.ooftf.widget.dagger

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by 99474 on 2017/11/17 0017.
 */
@Module
class SwipeModule(var context:Context){
    @Provides
    fun providesContext ():Context{
        return context
    }
}
