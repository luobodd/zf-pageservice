package com.zf.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 文件操作工具类
 */
@Slf4j
public class FileUtil {

	/**
	 * @function 单文件上传
	 * @return
	 */
	public static String uploadFile(MultipartFile file, String saveUrl, String fileType) {
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String fileName = IdWorker.getIdStr() + suffix;
		String newUrl = saveUrl + "\\" + "cover\\" + fileName;
		System.out.println("文件路径：" + newUrl);
		String[] type_array = fileType.split(",");
		System.out.println("type  :  " + type_array.length);
		File saveFile = new File(newUrl);
		if (!saveFile.getParentFile().exists()) {
			saveFile.getParentFile().mkdirs();
		}
		try {
			file.transferTo(saveFile);
		} catch (IOException e) {
			e.printStackTrace();
			return "上传失败！";
		}
		return fileName;
	}

	/**
	 * 保存文件
	 */
	public static List<File> createFile(String content, String basePath, String fileName) throws Exception {

		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64Utils.decode(content))) {
			// 准备保存的文件夹和文件名
			File folder = new File(String.valueOf(new File(basePath + "/")));
			File filePath = new File(folder, fileName);

			// 创建保存文件的目录
			if (!folder.exists()) {
				if (!folder.mkdirs()) {
					throw new RuntimeException(String.format("上传目录 %s 创建失败", folder.getAbsolutePath()));
				}
			}

			if (filePath.exists()) {
				throw new RuntimeException(String.format("文件 %s 已经存在？？？", filePath.getAbsolutePath()));
			}

			// 保存文件
			try (OutputStream out = new FileOutputStream(filePath)) {
				Streams.copy(byteArrayInputStream, out);
			}

			return ZipUtil.unZipFiles(String.valueOf(filePath),
					String.valueOf(folder + "/" + Times.format(new Date(), Times.DATETIME_PATTERN)));

		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param delpath
	 *            要删除的文件夹
	 * @return 是否成功
	 * @throws Exception
	 */
	// public static boolean deletefile(String delpath) throws Exception {
	//
	// try {
	// File file = new File(delpath);
	// // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
	// if (!file.isDirectory()) {
	// file.delete();
	// } else if (file.isDirectory()) {
	// String[] filelist = file.list();
	// for (int i = 0; i < filelist.length; i++) {
	// File delfile = new File(delpath + "/" + filelist[i]);
	// if (!delfile.isDirectory()) {
	// delfile.delete();
	// log.debug(delfile.getAbsolutePath() + "删除文件成功");
	// } else if (delfile.isDirectory()) {
	// deletefile(delpath + "/" + filelist[i]);
	// }
	// }
	// log.debug(file.getAbsolutePath() + "删除成功");
	// file.delete();
	// }
	//
	// } catch (FileNotFoundException e) {
	// log.error("deletefile() Exception:" + e.getMessage());
	// throw new RuntimeException(e);
	// }
	// return true;
	// }

	/**
	 * 删除一个月之前的文件
	 * 
	 * @param dirPath
	 * @return
	 */
	// public static boolean deleteTimeOutFile(String dirPath) {
	//
	// try {
	// File file = new File(dirPath);
	// // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
	// if (!file.isDirectory()) {
	// file.delete();
	// } else if (file.isDirectory()) {
	// String[] filelist = file.list();
	// for (int i = 0; i < filelist.length; i++) {
	// File delfile = new File(dirPath + "/" + filelist[i]);
	// if (!delfile.isDirectory()) {
	// delfile.delete();
	// System.out.println(delfile.getAbsolutePath() + "删除文件成功");
	// } else if (delfile.isDirectory() &&
	// Times.formatShort(filelist[i].substring(filelist[i].indexOf("_") +
	// 1)).getTime() > Times.getPreMonth().getTime()) {
	// deletefile(dirPath + "/" + filelist[i]);
	// }
	// }
	// log.debug(file.getAbsolutePath() + "删除成功");
	// file.delete();
	// }
	//
	// } catch (FileNotFoundException e) {
	// log.debug("deletefile() Exception:" + e.getMessage());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return true;
	// }

	/**
	 * GBK 转码 UTF-8
	 */
	public static String gbkToUtf(byte[] gbkString) throws UnsupportedEncodingException {
		return new String(gbkString, "gbk");
	}

	/**
	 * 直接读取 zip 文件内容，不进行解压
	 * 
	 * @param inDate
	 * @return
	 * @throws IOException
	 */
	// public static Map<String, byte[]> getZipData(byte[] inDate) throws
	// IOException {
	//
	// // 解压出的文件 Map<文件名, 数据>
	// Map<String, byte[]> files = new HashMap<>();
	// // 解压文件
	// try (ZipInputStream in = new ZipInputStream(new
	// ByteArrayInputStream(inDate))) {
	// // 遍历文件
	// ZipEntry zipEntry;
	// byte[] data = new byte[0];
	// while ((zipEntry = in.getNextEntry()) != null) {
	// // 当前文件的文件名
	// String fileName = zipEntry.getName();
	//
	// // 当前文件的数据
	// try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
	// byte[] buf = new byte[8192];
	// int len;
	// while ((len = in.read(buf, 0, buf.length)) != -1) {
	// out.write(buf, 0, len);
	// }
	//
	// data = out.toByteArray();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// // 保存当前文件
	// files.put(fileName, data);
	// }
	// }
	// return files;
	// }

	public static LineNumberReader readZipData(String fileStr, String saveDir, String fileType, String pub)
			throws Exception {

		// 存储压缩文件
		String fileName = fileType + Times.format(new Date(), Times.DATETIME_PATTERN) + ".zip";
		List<File> fileList = FileUtil.createFile(fileStr, saveDir, fileName);

		// 读取压缩文件
		// File recZip = new File(saveDir + "/" + fileName);
		// byte [] temp = new byte[2048];
		// Map<String, byte[]> zipFileData = new HashMap<>();
		// try(DataInputStream is = new DataInputStream(new FileInputStream(recZip))) {
		// while (-1 != is.read(temp)) {
		// zipFileData = FileUtil.getZipData(temp);
		// }
		// }

		Map<String, byte[]> zipFileData = new HashMap<>();
		byte[] data = new byte[0];
		Map<String, byte[]> files = new HashMap<>();
		for (File file : fileList) {
			try (FileInputStream in = new FileInputStream(file)) {
				try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
					byte[] buf = new byte[8192];
					int len;
					while ((len = in.read(buf, 0, buf.length)) != -1) {
						out.write(buf, 0, len);
					}

					data = out.toByteArray();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 保存当前文件
			files.put(file.getName(), data);
		}

		zipFileData = files;

		// 对账签名内容
		String sign = null;
		// 对账文件内容
		byte[] recContent = new byte[2048];
		for (Map.Entry<String, byte[]> entity : zipFileData.entrySet()) {
			if (entity.getKey().contains("QM")) {
				sign = new String(entity.getValue());
			} else {
				recContent = entity.getValue();
			}
		}

		// 验证文件签名
		boolean isSave = RSAUtils.verify(recContent, pub, sign);

		if (isSave) {
			log.debug("-----------------" + fileType + "验签成功------------------");
			try {
				LineNumberReader lineNumberReader = new LineNumberReader(
						new InputStreamReader(new ByteArrayInputStream(recContent)));
				return lineNumberReader;
			} catch (Exception e) {
				log.error("--------------文件读取失败啦");
			}
			return null;
		} else {
			log.debug("-----------------" + fileType + "验签失败------------------");
			throw new RuntimeException("对账文件签名验证失败");
		}
	}
}
