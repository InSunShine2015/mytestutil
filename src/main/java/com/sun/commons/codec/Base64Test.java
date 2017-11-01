package com.sun.commons.codec;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Test {
	/**
	 * 使用base64算法加密字符串
	 * @param plainText
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeByBase64(String plainText) throws UnsupportedEncodingException{
		byte[] b = plainText.getBytes("utf-8");
		b = Base64.encodeBase64(b);
		return new String(b);
	}
	/**
	 * 使用base64算法解密字符串
	 * @param secretText
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeByBase64(String secretText) throws UnsupportedEncodingException{
		byte[] b = Base64.decodeBase64(secretText);
		return new String(b,"utf-8");
	}
	
	public static void main(String[] args) {
		String str ="ceshi测试";
		try {
			String secret = encodeByBase64(str);
			System.out.println("加密后的字符串："+secret);
			String unsecret = decodeByBase64(secret);
			System.out.println("解密后的字符串："+unsecret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
