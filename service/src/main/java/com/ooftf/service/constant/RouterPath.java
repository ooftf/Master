package com.ooftf.service.constant;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public interface RouterPath {
    String MAIN_ACTIVITY_MAIN = "/main/activity/main";
    String SIGN_ACTIVITY_SIGN_IN = "/sign/activity/signIn";
    String SIGN_ACTIVITY_REGISTER = "/sign/activity/register";
    String SOURCE_ACTIVITY_RX_SUBJECT = "/source/activity/rxSubject";
    String APPLET_ACTIVITY_TEXT_TO_VOICE = "/applet/activity/textToVoice";
    String APPLET_ACTIVITY_WEEKLY_CONSUMPTION = "/applet/activity/weeklyConsumption";
    String OTHER_ACTIVITY_REFLECT_PERFORMANCE_TEST = "/other/activity/reflectPerformanceTest";
    String QRCODE_ACTIVITY_QRCODE = "/qrcode/activity/qrcode";
    String OTHER_ACTIVITY_TOUCH = "/other/activity/touch";


    String IM_ACTIVITY_MAIN = "/im/activity/main";
    String IM_ACTIVITY_PERSONAL_CHAT = "/im/activity/personalChat";
    String IM_ACTIVITY_GROUP_CHAT = "/im/activity/groupChat";


    interface Widget {
        String MODULE = "/widget";

        interface Activity {
            String ACTIVITY = MODULE + "/activity";
            String STATE_LAYOUT_SAMPLE = ACTIVITY + "/StateLayoutSample";
            String VIEWPAGER = ACTIVITY + "/viewPager";
            String PROGRESS_BAR = ACTIVITY + "/progressBar";

            String SCENE_DEMO = ACTIVITY + "/SceneDemo";
            String IMAGE_PREVIEW = ACTIVITY + "/ImagePreview";
        }

        interface Fragment {
            String FRAGMENT = MODULE + "/fragment";
            String MAIN = FRAGMENT + "/widget";
        }
    }

    interface Applet {
        String MODULE = "/applet";
        interface Activity {
            String ACTIVITY = MODULE + "/activity";
            String DuoWanMain = ACTIVITY + "/DuoWanMain";
        }

        interface Fragment {
            String FRAGMENT = MODULE + "/fragment";
            String MAIN = FRAGMENT + "/applet";
        }
    }

    interface Web {
        String MODULE = "/web";
        interface Activity {
            String ACTIVITY = MODULE + "/activity";
            String MAIN = ACTIVITY + "/main";
            String SHORTCUT = ACTIVITY + "/shortcut";
        }
    }

}
