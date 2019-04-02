package com.ooftf.master.qrcode.engine;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/8 0008
 */
public interface IPreviewCallback {
    void onPreview(ImageInfo info);

    class ImageInfo {
        int imageFormat;
        byte[] bytes;
        int width;
        int height;

        public ImageInfo(int imageFormat, byte[] bytes, int width, int height) {
            this.imageFormat = imageFormat;
            this.bytes = bytes;
            this.width = width;
            this.height = height;
        }


        public int getImageFormat() {
            return imageFormat;
        }

        public byte[] getBytes() {
            return bytes;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
