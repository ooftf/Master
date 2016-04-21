package com.master.kit.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class UrlParser {
	private String urlOriginal;
	private String address;
	private Map<String, String> params;

	public String getAddress() {
		return address;
	}

	public Map<String, String> getParams() {
		return params;
	}
	public UrlParser(String url) {
		params = new HashMap<>();
		this.urlOriginal = url;
		String[] ss = url.split("\\?");
		address = ss[0];
		if (ss.length > 1 && !isEmpty(ss[1])) {
			String[] entrys = ss[1].split("&");
			for (String s : entrys) {
				String[] kv = s.split("=");
				if (kv.length == 2 && !isEmpty(kv[0]) && !isEmpty(kv[1])) {
					params.put(kv[0], kv[1]);
				}
			}
		}
	}

	boolean isEmpty(String s) {
		if (s == null || s.length() == 0)
			return true;
		return false;
	}

	public String getUrlOriginal() {
		return urlOriginal;
	}

	public String getUrl() {
		String result = address;
		boolean isFirst = true;
		for (Map.Entry<String, String> en : params.entrySet()) {
			if (isFirst) {
				result += "?";
				isFirst = false;
			} else
				result += "&";
			result = result + en.getKey() + "=" + en.getValue();
		}
		return result;
	}
	public void putParam(String key,String value){
		params.put(key,value);
	}
}
