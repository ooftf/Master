package com.ooftf.master.qrcode.engine;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/8 0008
 */
public interface ICamera {

    void startPreview();

    void stopPreview();

    void setImageCallback(IPreviewCallback callback);
}
