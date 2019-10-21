package com.ooftf.master.webview.widget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.widget.Toast;

import com.nostra13.universalimageloader.utils.IoUtils;
import com.ooftf.master.widget.dialog.ui.ListDialog;
import com.ooftf.service.base.BaseApplication;
import com.ooftf.service.empty.EmptyObserver;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImgSrcHandlerDialog extends ListDialog {
    String src;

    public ImgSrcHandlerDialog(@NotNull Activity activity, String src) {
        super(activity);
        this.src = src;
        setList(new ArrayList<String>() {
            {
                add("保存图片");
            }
        });
        setOnItemClickListener((data, position, dialog) -> {
            if (position == 0) {
                saveImageUlrToAlbum(src);
            }
            dismiss();
        });
    }

    private static void saveImageUlrToAlbum(String url) {
        Observable
                .just(url)
                // 下载图片存成本地文件
                .flatMap((Function<String, ObservableSource<File>>) url1 -> {
                    //data:image/png;base64,
                    if (isBase64(url1)) {
                        return base64ToFile(url1);
                    } else {
                        //https
                        return getImageInputStream((url1))
                                // 从InputStream中拿到数据存储到本地
                                .flatMap((Function<InputStream, ObservableSource<File>>) inputStream -> streamToFile(inputStream, getFormat(url1)));
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EmptyObserver<File>() {
                    @Override
                    public void onNext(File file) {
                        BaseApplication.instance.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                        Toast.makeText(BaseApplication.instance, "保存成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        toasts(e.getMessage());
                    }
                });
    }

    private static Observable<File> base64ToFile(final String url) {
        final String base64 = url.replaceFirst("^data:image/[a-z]{3,4};base64,", "");
        return Observable.create(emitter -> {
            byte[] bytes = Base64.decode(base64, Base64.DEFAULT);
            String format = null;
            String[] legalFormat = getLegalFormat();
            for (int i = 0; i < legalFormat.length; i++) {
                if (url.contains(legalFormat[i])) {
                    format = legalFormat[i];
                    break;
                }
            }
            if (format == null) {
                format = legalFormat[0];
            }
            final File file = getFile(format);
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(bytes);

            } catch (IOException e) {
                emitter.onError(e);
                e.printStackTrace();
            } finally {
                IoUtils.closeSilently(fos);
            }
            emitter.onNext(file);
            emitter.onComplete();
        });

    }

    /**
     * data:image/png;base64,
     *
     * @param url
     * @return
     */
    private static boolean isBase64(String url) {
        if (url == null) {
            return false;
        }
        return url.matches("^data:image/[a-z]{3,4};base64,.+$");
    }

    public static void toasts(String message) {
        Toast.makeText(BaseApplication.instance, message, Toast.LENGTH_SHORT).show();
    }

    private static Observable<InputStream> getImageInputStream(final String imageUrl) {
        return Observable.create(emitter -> {
            try {
                Response response = new OkHttpClient.Builder()
                        .build()
                        .newCall(new Request.Builder().url(imageUrl).build())
                        .execute();
                if (emitter == null || emitter.isDisposed()) {
                    return;
                }
                if (response.code() == 200) {
                    emitter.onNext(response.body().byteStream());
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception("下载图片失败"));
                }
            } catch (Exception e) {
                if (emitter == null || emitter.isDisposed()) {
                    return;
                }
                emitter.onError(new Exception("下载图片失败"));
            }


        });

    }

    /**
     * 获取url中的图片格式，并进行矫正
     *
     * @param imageUrl
     * @return
     */
    private static String getFormat(String imageUrl) {
        String[] legalFormat = getLegalFormat();
        String result = null;
        String[] split = imageUrl.split("\\.");
        if (split.length == 2) {
            result = split[1];
        }
        if (result == null) {
            return legalFormat[0];
        }
        for (int i = 0; i < legalFormat.length; i++) {
            if (result.equalsIgnoreCase(legalFormat[i])) {
                return legalFormat[i];
            }
        }
        return legalFormat[0];
    }

    private static String[] getLegalFormat() {
        return new String[]{"jpg", "png", "bmp", "jpeg", "gif"};
    }

    /**
     * 将流中的数据保存到文件并关闭流
     *
     * @param is
     * @param format
     * @return
     */
    private static Observable<File> streamToFile(final InputStream is, final String format) {
        return Observable.create(emitter -> {
            File file = getFile(format);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                IoUtils.copyStream(is, fos, null);
                fos.flush();
                if (emitter == null || emitter.isDisposed()) {
                    return;
                }
                emitter.onNext(file);
                emitter.onComplete();
            } catch (IOException e) {
                e.printStackTrace();
                if (emitter == null || emitter.isDisposed()) {
                    return;
                }
                emitter.onError(new Exception("保存失败"));
            } finally {
                IoUtils.closeSilently(fos);
                IoUtils.closeSilently(is);

            }
        });

    }

    private static File getFile(String format) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "JmImage");
        if (!appDir.exists()) appDir.mkdirs();
        String fileName = System.currentTimeMillis() + "." + format;
        return new File(appDir, fileName);
    }

}
