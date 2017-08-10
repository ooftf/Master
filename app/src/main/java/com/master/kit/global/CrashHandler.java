package com.master.kit.global;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 处理APP崩溃信息
 * 当检测到应用崩溃，会先将崩溃信息存储到本地文件夹中
 * 当下次开打应用到{@link }的时候，提交崩溃日志到服务器中
 * 使用方式：CrashHandler.init(this);
 */
public  class CrashHandler implements UncaughtExceptionHandler {

	public static final String SP_NAME_ERROR_LOG = "error_log";
	public static final String SP_KEY_USER_ID = "userId";
	public static final String SP_KEY_FILE_PATH = "path";

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			String filename = getLogFilePath();
			writeLog(sw.toString(),filename);
			saveSpInfo(filename);
		}
		if(defaultUncaughtExceptionHandler!=null){
			defaultUncaughtExceptionHandler.uncaughtException(thread,ex);
		}
	}

	/**
	 * 获取存放日志的路径  包括文件名字
	 * @return
     */
	@NonNull
	private String getLogFilePath() {
		String sdcardPath = Environment.getExternalStorageDirectory().getPath();
		String filePath = sdcardPath + "/ETongDai";
		File file = new File(filePath);
		if(!file.exists()){
            file.mkdirs();
        }
		return filePath+"/ETongDai_"+ System.currentTimeMillis()+".log";
	}

	/**
	 * 保存崩溃文件路径和用户ID到SP中
	 * @param filename
     */
	private void saveSpInfo(String filename) {
		SharedPreferences sp = application.getSharedPreferences(SP_NAME_ERROR_LOG, Context.MODE_PRIVATE);
		sp.edit().putString(SP_KEY_USER_ID,getUserId()).putString(SP_KEY_FILE_PATH,filename).apply();
	}

	/**
	 * 获取userId  如果没有登录返回null
	 * @return
     */
	protected  String getUserId(){
		return null;
	}
	Application application;
	private CrashHandler(Application application) {
		this.application = application;
		defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}
	UncaughtExceptionHandler defaultUncaughtExceptionHandler;

	/**
	 * 初始化
	 * @param application
     */
	public static void  init(Application application){
		new CrashHandler(application);
	}

	/**
	 * 将日志写入文件
	 * @param log
	 * @param path
     */
	private void writeLog(String log, String path)
	{
		try
		{
			FileOutputStream stream = new FileOutputStream(path);
			OutputStreamWriter output = new OutputStreamWriter(stream);
			BufferedWriter bw = new BufferedWriter(output);
			bw.write(log);
			bw.newLine();
			bw.close();
			output.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 保存userId
	 * @param context
	 * @return
     */
	public static String getSpUserId(Context context){
		return context.getSharedPreferences(SP_NAME_ERROR_LOG,context.MODE_PRIVATE).getString(SP_KEY_USER_ID,null);
	}

	/**
	 * 保存日志路径
	 * @param context
	 * @return
     */
	public static String getSpFilePath(Context context){
		return context.getSharedPreferences(SP_NAME_ERROR_LOG,context.MODE_PRIVATE).getString(SP_KEY_FILE_PATH,null);
	}

	/**
	 * 清除sp数据
	 * @param context
     */
	public static void clearSp(Context context){
		context.getSharedPreferences(SP_NAME_ERROR_LOG,context.MODE_PRIVATE).edit().clear().apply();
	}

	/**
	 *
	 * @param context
	 * @param requestHandler
	 * @return   true 为检测到有未提交的日志，false为没有可提交的日志
	 * @throws IOException
     */
	/*public static boolean submitLog(final Context context,BaseHandler requestHandler) throws IOException {
		String filePath = CrashHandler.getSpFilePath(context);
		String userId = CrashHandler.getSpUserId(context);
		if (filePath == null) return false;
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		StringBuffer stringBuffer = new StringBuffer();
		String s;
		while ((s = reader.readLine()) != null) {
			stringBuffer.append(s);
		}
		reader.close();
		HashMap<String, String> params = new HashMap<>();
		params.put("mobileBrand", Build.BRAND);
		params.put("mobileModel", Build.MODEL);
		params.put("cpuabi1", Build.CPU_ABI);
		params.put("cpuabi2", Build.CPU_ABI2);
		params.put("systemVersion", Build.VERSION.SDK);
		params.put("errorDesc", stringBuffer.toString());
		params.put("fromPlatform","1-4");//1-4  代表易通贷Android平台
		params.put("useId2", userId);
		params.put("platformVersion", AndroidUtil.getVersion(context));
		new RequestCommand().requestErrorLog(requestHandler, context, params);
		return true;
	}*/
	/**
	 *
	 * 01-03 11:02:04.385 19871-19871/com.stateunion.p2p.etongdai E/Build.MODEL: MI NOTE LTE
	 01-03 11:02:04.385 19871-19871/com.stateunion.p2p.etongdai E/Build.BRAND/: Xiaomi
	 01-03 11:02:04.385 19871-19871/com.stateunion.p2p.etongdai E/Build.BOARD/: MSM8974
	 01-03 11:02:04.385 19871-19871/com.stateunion.p2p.etongdai E/Build.BOOTLOADER/: unknown
	 01-03 11:02:04.385 19871-19871/com.stateunion.p2p.etongdai E/Build.DEVICE/: virgo
	 01-03 11:02:04.385 19871-19871/com.stateunion.p2p.etongdai E/Build.DISPLAY/: MMB29M
	 01-03 11:02:04.385 19871-19871/com.stateunion.p2p.etongdai E/Build.FINGERPRINT/: Xiaomi/virgo/virgo:6.0.1/MMB29M/6.12.29:user/release-keys
	 01-03 11:02:04.385 19871-19871/com.stateunion.p2p.etongdai E/Build.HARDWARE/: qcom
	 01-03 11:02:04.386 19871-19871/com.stateunion.p2p.etongdai E/Build.HOST/: c3-miui-ota-bd22.bj
	 01-03 11:02:04.386 19871-19871/com.stateunion.p2p.etongdai E/Build.ID/: MMB29M
	 01-03 11:02:04.386 19871-19871/com.stateunion.p2p.etongdai E/Build.MANUFACTURER/: Xiaomi
	 01-03 11:02:04.386 19871-19871/com.stateunion.p2p.etongdai E/Build.PRODUCT/: virgo
	 01-03 11:02:04.386 19871-19871/com.stateunion.p2p.etongdai E/Build.SERIAL/: a1743350
	 01-03 11:02:04.386 19871-19871/com.stateunion.p2p.etongdai E/Build.TAGS/: release-keys
	 01-03 11:02:04.386 19871-19871/com.stateunion.p2p.etongdai E/Build.TYPE/: user
	 01-03 11:02:04.386 19871-19871/com.stateunion.p2p.etongdai E/Build.UNKNOWN/: unknown
	 01-03 11:02:04.386 19871-19871/com.stateunion.p2p.etongdai E/Build.USER/: builder
	 01-03 11:02:04.387 19871-19871/com.stateunion.p2p.etongdai E/Build.CPU_ABI/: armeabi-v7a
	 01-03 11:02:04.387 19871-19871/com.stateunion.p2p.etongdai E/Build.CPU_ABI2/: armeabi
	 01-03 11:02:04.387 19871-19871/com.stateunion.p2p.etongdai E/Build.RADIO/: unknown
	 01-03 11:02:04.387 19871-19871/com.stateunion.p2p.etongdai E/Build.Build/: 23
	 */

}
