package com.digisold.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

public class ToolUtil {

	/**
	 * MD5加密 MD5
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static String MD5Encoder(String str) {
		String result = "";
		try {
			java.security.MessageDigest alg = java.security.MessageDigest.getInstance("MD5");
			alg.update(str.getBytes());
			byte[] digesta = alg.digest();
			for (int i = 0; i < digesta.length; i++) {
				int m = digesta[i];
				if (m < 0) {
					m += 256;
				}
				if (m < 16) {
					result += "0";
				}
				result = result + Integer.toString(m, 16);
			}
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * Get UUID
	 */
	static TimeBasedGenerator generator = Generators.timeBasedGenerator();

	public static String getUUID() {
		return generator.generate().toString();
	}

	/**
	 * 上传文件
	 * 
	 * @param uploadFolder
	 *            上传文件的路径
	 * @param ins
	 *            文件流
	 * @return
	 */
	public static String uploadFile(String uploadFolder, String filename, InputStream ins) {
		String newfilename = null;
		OutputStream outs = null;
		try {
			if (ins == null) {
				return null;
			}
			File directory = new File(uploadFolder);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			newfilename = getUUID() + filename.substring(filename.lastIndexOf("."));
			outs = new FileOutputStream(uploadFolder + File.separator + newfilename);
			int bytes = 0;
			byte[] byteFile = new byte[1024];
			while ((bytes = ins.read(byteFile)) != -1) {
				outs.write(byteFile, 0, bytes);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ins != null) {
					ins.close();
				}
				if (outs != null) {
					outs.close();
				}
			} catch (IOException ioEx) {
				ioEx.printStackTrace();
			}
		}
		return newfilename;
	}

	public static Date dateParse(String dtFmt, String dateStr) {
		Date date = null;
		try {
			date = new SimpleDateFormat(dtFmt, Locale.CHINA).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String dateFormat(String dtFmt, Date date) {
		return new SimpleDateFormat(dtFmt, Locale.CHINA).format(date);
	}

}
