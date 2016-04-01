package com.alumniHelper.util;


import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReqUtil {

	public static String getRequestContent(HttpServletRequest req) {
		StringBuffer content = new StringBuffer();
		try {
			InputStream is = req.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				content.append(line);
			}
		} catch (Exception e) {
			return null;
		}
		return content.toString();
	}

}
