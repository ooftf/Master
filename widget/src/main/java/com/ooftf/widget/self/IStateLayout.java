package com.ooftf.widget.self;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/5/8 0008
 */
interface IStateLayout {
    void switchToEmpty();

    void switchToLoading();

    void switchToError();

    void switchToNormal();
}
