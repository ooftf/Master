package com.master.kit

/**
 * Created by 99474 on 2017/11/22 0022.
 */
class KotlinInitSuper constructor(i: Int, s: String, a: A) {
    constructor() : this(1, "", A()) {
        println("constructor1")
    }

    constructor(i: Int) : this() {
        println("constructor2")
    }

    init {
        println("KotlinInitSuper onCreate")
    }
}