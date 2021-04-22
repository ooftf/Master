package com.master.kit.matrix

import com.tencent.mrs.plugin.IDynamicConfig

/**
 * MatrixEnum
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/4/22
 */
class MyDynamicConfig: IDynamicConfig {
    override fun get(key: String?, defStr: String?): String {
        return defStr?:""
    }

    override fun get(key: String?, defInt: Int): Int {
        return defInt
    }

    override fun get(key: String?, defLong: Long): Long {
        return defLong
    }

    override fun get(key: String?, defBool: Boolean): Boolean {
        return defBool
    }

    override fun get(key: String?, defFloat: Float): Float {
        return defFloat
    }
}