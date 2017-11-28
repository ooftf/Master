package com.master.kit

import org.junit.Test

/**
 * Created by 99474 on 2017/11/28 0028.
 */
class KotlinUnitTest {
    @Test fun forTest(){
        for (i in 0 until 100){
            println(i.toString())
        }
        /*for (i in (0..100)) {

        }*/
        for (i in 0 .. 100){
            println(i.toString())
        }
    }
}