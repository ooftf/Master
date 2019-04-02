package com.ooftf.master.qrcode.utils;

import android.graphics.Bitmap;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.QRCodeReader;
import com.ooftf.service.utils.JLog;

import io.reactivex.Observable;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/13 0013
 */
public class QrDecoder {
    MultiFormatReader multiFormatReader;

    QRCodeMultiReader qrCodeMultiReader;

    public QrDecoder() {

    }

    public Observable<String> decodePreviewByte(byte[] data, int width, int height) {
        JLog.e(width);
        JLog.e(height);
        return Observable.fromCallable(() -> {
            Result decode;
            PlanarYUVLuminanceSource source = new PlanarYUVLuminanceSource(data, width, height, 0, 0, width, height, false);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            decode = getMultiFormatReader().decode(bitmap);
            return decode.getText();
        });
    }

    public Observable<String> getQRResult(final Bitmap bitmap, final float focusX, final float focusY) {
        return Observable.create(emitter -> {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] data = new int[width * height];
            bitmap.getPixels(data, 0, width, 0, 0, width, height);
            RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result[] results;
            results = getQrCodeMultiReader().decodeMultiple(binaryBitmap);
            if (results == null || results.length == 0) {
                emitter.onError(new Exception("解析二维码出错"));
            } else if (results.length == 1) {
                emitter.onNext(results[0].getText());
                emitter.onComplete();
            } else {
                double distance = Double.MAX_VALUE;
                int index = 0;
                for (int i = 0; i < results.length; i++) {
                    Result result = results[i];
                    ResultPoint[] resultPoints = result.getResultPoints();
                    float x = 0;
                    float y = 0;
                    for (ResultPoint point : resultPoints) {
                        x += point.getX();
                        y += point.getY();
                    }
                    x = x / resultPoints.length;
                    y = y / resultPoints.length;
                    double temp = (x - focusX) * (x - focusX) + (y - focusY) * (y - focusY);
                    if (temp <= distance) {
                        distance = temp;
                        index = i;
                    }
                }
                emitter.onNext(results[index].getText());
                emitter.onComplete();
            }
        });

    }

    public MultiFormatReader  getMultiFormatReader() {
        if (multiFormatReader == null) {
            multiFormatReader = new MultiFormatReader ();
        }
        return multiFormatReader;
    }

    public QRCodeMultiReader getQrCodeMultiReader() {
        if (qrCodeMultiReader == null) {
            qrCodeMultiReader = new QRCodeMultiReader();
        }
        return qrCodeMultiReader;
    }
}
