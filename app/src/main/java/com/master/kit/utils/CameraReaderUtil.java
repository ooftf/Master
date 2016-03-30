package com.master.kit.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class CameraReaderUtil {

	public static String getImagePathFromUri(Uri imageUri, Context context) {
		String[] filePathColumns = { MediaStore.Images.Media.DATA };
		Cursor c = context.getContentResolver().query(imageUri,
				filePathColumns, null, null, null);
		if (c == null) {
			System.out.println("c---------------" + c);
			return imageUri.getPath();
		}
		c.moveToFirst();
		String imagePath = c.getString(c.getColumnIndex(filePathColumns[0]));
		c.close();
		if (imagePath == null) {

			return imageUri.getPath();
		}
		System.out.println("imagePath---------------" + imagePath);
		return imagePath;
	}

	public static Bitmap readBitmapFromPath(String imagePath, int reqWidth,
												int reqHeight) throws FileNotFoundException {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		InputStream is = new FileInputStream(imagePath);
		BitmapFactory.decodeStream(is, null, options);
		if (options.mCancel || options.outWidth == -1
				|| options.outHeight == -1) {
			return null;
		}
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		options.inDither = false;
		options.inPurgeable = true;
		options.inInputShareable = true;

		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeStream(is, null, options);
	}

	private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
											 int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (reqHeight == -1) {// °´ÕÕ¿í¶ÈÑ¹Ëõ
			if (width < reqWidth) {
				inSampleSize = 1;
			} else {
				inSampleSize = width / reqWidth;

			}
		} else if (reqWidth == -1) {
			if (height < reqHeight) {// °´ÕÕ¸ß¶È¶ÈÑ¹Ëõ
				inSampleSize = 1;
			} else {
				inSampleSize = height / reqHeight;
			}
		} else if (reqWidth < reqHeight) {
			inSampleSize = height / reqHeight;
			inSampleSize = (inSampleSize == 0) ? 1 : inSampleSize;
			while (width / inSampleSize > reqWidth) {
				inSampleSize++;
			}
		} else {
			inSampleSize = width / reqWidth;
			inSampleSize = (inSampleSize == 0) ? 1 : inSampleSize;
			while (height / inSampleSize > reqHeight) {
				inSampleSize++;
			}
		}
		System.out.println("inSampleSize-----------" + inSampleSize);
		return inSampleSize;
	}

	@SuppressLint("NewApi")
	public static String getImageAbsolutePath(Activity context, Uri imageUri) {
		if (context == null || imageUri == null)
			return null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT
				&& DocumentsContract.isDocumentUri(context, imageUri)) {
			if (isExternalStorageDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/"
							+ split[1];
				}
			} else if (isDownloadsDocument(imageUri)) {
				String id = DocumentsContract.getDocumentId(imageUri);
				Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));
				return getDataColumn(context, contentUri, null, null);
			} else if (isMediaDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}
				String selection = MediaStore.Images.Media._ID + "=?";
				String[] selectionArgs = new String[] { split[1] };
				return getDataColumn(context, contentUri, selection,
						selectionArgs);
			}
		} // MediaStore (and general)
		else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
			// Return the remote address
			if (isGooglePhotosUri(imageUri))
				return imageUri.getLastPathSegment();
			return getDataColumn(context, imageUri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
			return imageUri.getPath();
		}
		return null;
	}

	public static String getDataColumn(Context context, Uri uri,
									   String selection, String[] selectionArgs) {
		Cursor cursor = null;
		String column = MediaStore.Images.Media.DATA;
		String[] projection = { column };
		try {
			cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri
				.getAuthority());
	}

	public static Bitmap readBitmapFromUri(Uri uri, long requestPixels,
										   Context context) throws IOException {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		options.inJustDecodeBounds = true;
		InputStream is = context.getContentResolver().openInputStream(uri);
		BitmapFactory.decodeStream(is, null, options);
		if (options.mCancel || options.outWidth == -1
				|| options.outHeight == -1) {
			return null;
		}
		long picSize = options.outHeight * options.outWidth;
		System.out.println("picSize" + picSize);
		options.inSampleSize = (int) (picSize / requestPixels);
		if (options.inSampleSize <= 1) {
			options.inSampleSize = 1;
		}
		is.close();

		options.inPurgeable

				= true; // Tell to gc that whether it needs free memory, the Bitmap can
		// be cleared
		options.inInputShareable = true; // Which kind of reference will be used
		// to recover the Bitmap data after
		// being clear, when it will be used
		// in the future
		options.inJustDecodeBounds = false;
		is = context.getContentResolver().openInputStream(uri);
		Bitmap resultBitmap = BitmapFactory.decodeStream(is, null, options);

		// options.inPreferredConfig = Config.ARGB_8888;
		is.close();
		return resultBitmap;
	}


	/**
	 *
	 * @param path
	 *            ´æ·ÅÍ¼Æ¬µÄÂ·¾¶
	 * @param requestSize
	 *            ·µ»ØÍ¼Æ¬µÄ´óÐ¡,µ¥Î»Îª×Ö½Ú
	 * @param context
	 * @return
	 * @throws IOException
	 */
	public static Bitmap readBitmappFromPath(String path, long requestSize,
											 Context context) throws IOException {
		requestSize = 1000 * 1000;
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		if (options.mCancel || options.outWidth == -1
				|| options.outHeight == -1) {
			return null;
		}
		// ¼ÆËãÍ¼Æ¬µÄ´óÐ¡
		File file = new File(path);
		// long picSize = file.length();
		long picSize = options.outHeight * options.outWidth;
		System.out.println("picSize" + picSize);
		options.inSampleSize = (int) (picSize / requestSize);
		if (options.inSampleSize <= 1) {
			options.inSampleSize = 1;
		}

		// options.inPreferredConfig = Config.RGB_565;
		// Decode bitmap with inSampleSize set
		options.inDither = false;
		options.inPurgeable

				= true; // Tell to gc that whether it needs free memory, the Bitmap can
		// be cleared
		options.inInputShareable = true; // Which kind of reference will be used
		// to recover the Bitmap data after
		// being clear, when it will be used
		// in the future
		options.inJustDecodeBounds = false;
		Bitmap resultBitmap = BitmapFactory.decodeFile(path, options);
		return resultBitmap;
	}

}
