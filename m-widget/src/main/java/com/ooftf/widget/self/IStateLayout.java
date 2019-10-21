package com.ooftf.widget.self;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/5/8 0008
 */
interface IStateLayout {
    int STATE_SUCCESS = 0;
    int STATE_LOAD = 1;
    int STATE_EMPTY = 2;
    int STATE_ERROR = 3;
    void switchToEmpty();

    void switchToLoading();

    void switchToError();

    void switchToSuccess();
}
