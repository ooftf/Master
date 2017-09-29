package tf.oof.com.service.utils;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.Toast;

public class SystemIntent {
	public static class SelectPhoneNum {
		public static void start(Activity activity, int REQUEST_CONTACT) {
			Intent intent = new Intent();

			intent.setAction(Intent.ACTION_PICK);

			intent.setData(ContactsContract.Contacts.CONTENT_URI);

			activity.startActivityForResult(intent, REQUEST_CONTACT);
		}

		public static Contact getContact(Activity activity, Intent data) {

			if (data == null) {

				return null;

			}
			ArrayList<String> list = new ArrayList<>();
			ContentResolver reContentResolverol = activity.getContentResolver();
			Uri contactData = data.getData();
			Cursor cursor = reContentResolverol.query(contactData, null, null, null, null);
			cursor.moveToFirst();
			String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
			// 获得DATA表中的电话号码，条件为联系人ID,因为手机号码可能会有多个
			Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);

			while (phone.moveToNext()) {
				String userNumber = phone
						.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				list.add(userNumber);
			}
			phone.close();
			Contact contact = new Contact();
			contact.name = name;
			contact.phone = list;
			return contact;

		}

		public static class Contact {
			public String name;
			public ArrayList<String> phone;
		}
	}
}
