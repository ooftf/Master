package tf.oof.com.service;

import org.junit.Test;

import tf.oof.com.service.utils.Math;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void power_isCorrect() throws Exception {
        System.out.println(Math.INSTANCE.power(1.1,4));
        System.out.println(Math.INSTANCE.distance(1,2,2,1));
        //assertEquals(16, Math.power(2,4));
    }
}