package com.master.kit;

import com.master.kit.testcase.banner.BannerBean;

import org.junit.Test;

import kotlin.text.Regex;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println("sbs6-----" + BannerBean.class.getSimpleName().hashCode());
        System.out.println("sbs7-----" + BannerBean.class.getName().hashCode());
        System.out.println("sbs8-----" + BannerBean.class.getSimpleName().hashCode());
        System.out.println("sbs8-----" + "BannerBean".hashCode());
        assertEquals(4, 2 + 2);
    }

    @Test
    public void logCode() {
        BannerBean bean = new BannerBean();
        System.out.println("sb1-----" + bean.hashCode());
        // Log.e("bean",""+bean.hashCode());
        bean.setCode("sssssddd");
        bean.setInfo("5fasdfsdafsfdsafd");
        bean.setBody(new BannerBean.BodyEntity());
        //Log.e("bean",""+bean.hashCode());
        System.out.println("sb2-----" + bean.hashCode());
        System.out.println("sb5-----" + new BannerBean().hashCode());
        System.out.println("sbs6-----" + BannerBean.class.getSimpleName().hashCode());
        System.out.println("sbs7-----" + BannerBean.class.getSimpleName().hashCode());
        System.out.println("sbs8-----" + BannerBean.class.getSimpleName().hashCode());
        assertEquals(4, 2 + 2);
    }

    @Test
    public void kotlin() {
        System.out.println("start");
        new KotlinInitSuper();
        System.out.println("-----------------");
        new KotlinInitSuper(5);
        System.out.println("-----------------");
        new KotlinInitSuper(5, "456", new A());
    }
    @Test
    public void re(){
        Regex regex = new Regex("^[^\\u4e00-\\u9fa5]+$");
    }
}