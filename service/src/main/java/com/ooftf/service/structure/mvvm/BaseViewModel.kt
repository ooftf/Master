package com.ooftf.service.structure.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ooftf.service.structure.mvvm.ui.BaseLiveData

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/23 0023
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var baseLiveData = BaseLiveData()
}