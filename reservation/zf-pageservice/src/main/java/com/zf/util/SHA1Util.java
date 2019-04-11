package com.zf.util;

import java.security.MessageDigest;

public class SHA1Util {

	/**
	 * 32位摘要
	 * 
	 * @param sourceStr 源字符串
	 * @return 摘要
	 * @throws UException
	 */
	public static String digest(String src) throws Exception {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(src.getBytes("UTF-8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (Exception e) {
			throw new Exception("SHA-1加密失败", e);
		}
	}
	
	public static void main(String[] args) throws Exception {
        String a = "测试";
        String prikey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKjbIngpCo/112JfSlr7spU3ofByck/HmnyczZpZYYnhORPB7felZIluT6BIFcP10K3ey8/B/QBFgx/5XfYLVMHZdwILCyofGJJjZSL"
        		+ "Kf5W3ATX1AQUA4KviKjjCpAd4qk93dLqRuOi4tcSmBA5isnSszs8gK2RNfyrJyGNj5GCxAgMBAAECgYA6pMim5NIXR3GDZ8biLHJMYqLqaEUNMiyURL0fcxVuFnuFQiAMlzOAi4A6EW8FvNS53hv4zs24"
        		+ "j2vH6gpZDnxjui8o0ms9WuwWYSAEmkpsUiVzCjLo4afpDiN1AlcYGRcZ8InCxClsZWmuUwVMsdn9uWUy1VePtf1BouDI2+MhxQJBANsE3rnJO0T2GFASSvbyUcLWalgxDTfJQ2z9Yi2UMbJIt8J4oH8n"
        		+ "6qoyO+kYG+NU+vLP4uePcjA/GiOF12aajecCQQDFXfXQx2KHYhxZaZDRkm6tB9BrSoGmDvQMKh1dJntyGlcvakQMYNM9cq1oVbs83+SPdZcslNNboNGP0RwDz9mnAkEAnjPvWg3URSTqPPRpt+UjT/Sn"
        		+ "TlroLFKx5IlGoJu6JgjGy69sZ00DAbtVcTLb+iLJPhkR0qfYGKeMDvq/s7qSIwJAFMtuj93CEtOSexU1Te9ou9Bb9EH4YP+oVPPG/j6EuSPvVa6Tlt5Iw2umu5x0ytgoI7lXATp4/ml2SvnUQs6OdwJA"
        		+ "eUZ92uotPvdtbm/+yVvO7l7aLlCSynujBuCPrmctKF0mBQm8EhedYUFBiot9TF+FSzgKcOQTtGKIl2u+tV/BYA==";
        String sign = SHA1Util.digest(a+prikey);
        System.out.println(sign);
    }
}
