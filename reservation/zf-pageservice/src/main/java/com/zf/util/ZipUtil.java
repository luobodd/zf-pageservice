package com.zf.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.*;


/**
 * 通过Java的Zip输入输出流实现压缩和解压文件
 * 
 * @author zhang_xin
 * 
 */
@Slf4j
public final class ZipUtil {
	 
	private static final String alipayZipFileCharset = "GBK";
	
	public static final String EXT = ".zip";  
    private static final String BASE_DIR = "";
  
    // 符号"/"用来作为目录标识判断符
    private static final String PATH = "/";
    private static final int BUFFER = 1024;
  
    /** 
     * 压缩 
     *  
     * @param srcFile 
     * @throws Exception 
     */  
    public static void compress(File srcFile) throws Exception {  
        String name = srcFile.getName();  
        String basePath = srcFile.getParent();  
        String destPath = basePath + name + EXT;  
        compress(srcFile, destPath);  
    }  
  
    /** 
     * 压缩 
     *  
     * @param srcFile
     *            源路径
     * @param destFile
     *            目标路径
     * @throws Exception
     */  
    public static void compress(File srcFile, File destFile) throws Exception {  
  
        // 对输出文件做CRC32校验  
        CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(  
                destFile), new CRC32());  
  
        ZipOutputStream zos = new ZipOutputStream(cos);  
  
        compress(srcFile, zos, BASE_DIR);  
  
        zos.flush();  
        zos.close();  
    }  
  
    /** 
     * 压缩文件 
     *  
     * @param srcFile 
     * @param destPath 
     * @throws Exception 
     */  
    public static void compress(File srcFile, String destPath) throws Exception {  
        compress(srcFile, new File(destPath));  
    }  
  
    /** 
     * 压缩 
     *  
     * @param srcFile 
     *            源路径 
     * @param zos 
     *            ZipOutputStream 
     * @param basePath 
     *            压缩包内相对路径 
     * @throws Exception 
     */  
    private static void compress(File srcFile, ZipOutputStream zos,  
            String basePath) throws Exception {  
        if (srcFile.isDirectory()) {  
            compressDir(srcFile, zos, basePath);  
        } else {  
            compressFile(srcFile, zos, basePath);  
        }  
    }  
  
    /** 
     * 压缩 
     *  
     * @param srcPath 
     * @throws Exception 
     */  
    public static void compress(String srcPath) throws Exception {  
        File srcFile = new File(srcPath);  
  
        compress(srcFile);  
    }  
  
    /** 
     * 文件压缩 
     *  
     * @param srcPath 
     *            源文件路径 
     * @param destPath 
     *            目标文件路径 
     *  
     */  
    public static void compress(String srcPath, String destPath)  
            throws Exception {  
        File srcFile = new File(srcPath);  
  
        compress(srcFile, destPath);  
    }  
  
    /** 
     * 压缩目录 
     *  
     * @param dir 
     * @param zos 
     * @param basePath 
     * @throws Exception 
     */  
    private static void compressDir(File dir, ZipOutputStream zos,  
            String basePath) throws Exception {  
  
        File[] files = dir.listFiles();  
  
        // 构建空目录  
        if (files.length < 1) {  
            ZipEntry entry = new ZipEntry(basePath + dir.getName() + PATH);  
  
            zos.putNextEntry(entry);  
            zos.closeEntry();  
        }  
  
        for (File file : files) {  
  
            // 递归压缩  
            compress(file, zos, basePath + dir.getName() + PATH);  
  
        }  
    }  
  
    /** 
     * 文件压缩 
     *  
     * @param file 
     *            待压缩文件 
     * @param zos 
     *            ZipOutputStream 
     * @param dir 
     *            压缩文件中的当前路径 
     * @throws Exception 
     */  
    private static void compressFile(File file, ZipOutputStream zos, String dir)  
            throws Exception {  
  
        /** 
         * 压缩包内文件名定义 
         *  
         * <pre> 
         * 如果有多级目录，那么这里就需要给出包含目录的文件名 
         * 如果用WinRAR打开压缩包，中文名将显示为乱码 
         * </pre> 
         */  
        ZipEntry entry = new ZipEntry(dir + file.getName());  
  
        zos.putNextEntry(entry);  
  
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(  
                file));  
  
        int count;  
        byte data[] = new byte[BUFFER];  
        while ((count = bis.read(data, 0, BUFFER)) != -1) {  
            zos.write(data, 0, count);  
        }  
        bis.close();  
  
        zos.closeEntry();  
    }  
	    
    private ZipUtil() {
        // empty
    }
 
    /** 
     * 解压到指定目录 
     * @param zipPath 
     * @param descDir 
     * @author isea533 
     */  
    public static List<File> unZipFiles(String zipPath,String descDir)throws IOException{  
       return unZipFiles(new File(zipPath), descDir);  
    } 
    public static List<File> unZipFiles(File zipFile,String descDir)throws IOException{
    	List<File> fileList = new ArrayList<File>();
        File pathFile = new File(descDir);  
        if(!pathFile.exists()){  
            pathFile.mkdirs();  
        }  
        ZipFile zip = new ZipFile(zipFile);  
        for(Enumeration entries = zip.entries();entries.hasMoreElements();){  
            ZipEntry entry = (ZipEntry)entries.nextElement();  
            String zipEntryName = entry.getName();  
            InputStream in = zip.getInputStream(entry);  
            String outPath = (descDir+File.separator+zipEntryName).replaceAll("\\*", "/"); 
            //判断路径是否存在,不存在则创建文件路径  
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));  
            if(!file.exists()){  
                file.mkdirs();  
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压  
            File outFile = new File(outPath);
            fileList.add(outFile);
            if(outFile.isDirectory()){  
                continue;  
            }  
            //输出文件路径信息  
            System.out.println(outPath);  
              
            OutputStream out = new FileOutputStream(outPath);  
            byte[] buf1 = new byte[1024];  
            int len;  
            while((len=in.read(buf1))>0){  
                out.write(buf1,0,len);  
            }  
            in.close();  
            out.close();  
            }  
        log.debug("******************解压完毕********************");
        return fileList;
    }  
    /** 
     * 解压缩zip包 
     * @param zipFilePath zip文件的全路径 
     * @param unzipFilePath 解压后的文件保存的路径 
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含 
     */  
    @SuppressWarnings("unchecked")  
    public static List<File> unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName) throws Exception  
    {  
    	List<File> fileList = new ArrayList<File>();
        if (StringUtils.isEmpty(zipFilePath) || StringUtils.isEmpty(unzipFilePath))
        {  
            throw new Exception();            
        }  
        File zipFile = new File(zipFilePath);  
        //如果解压后的文件保存路径包含压缩文件的文件名，则追加该文件名到解压路径  
        if (includeZipFileName)  
        {  
            String fileName = zipFile.getName();  
            if (StringUtils.isNotEmpty(fileName))
            {  
                fileName = fileName.substring(0, fileName.lastIndexOf("."));  
            }  
            unzipFilePath = unzipFilePath + File.separator + fileName;  
        }  
        //创建解压缩文件保存的路径  
        File unzipFileDir = new File(unzipFilePath);  
        if (!unzipFileDir.exists() || !unzipFileDir.isDirectory())  
        {  
            unzipFileDir.mkdirs();  
        }  
          
        //开始解压  
        ZipEntry entry = null;  
        String entryFilePath = null, entryDirPath = null;  
        File entryDir = null;  
        int index = 0, count = 0, bufferSize = 1024;  
        byte[] buffer = new byte[bufferSize];  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        ZipFile zip = new ZipFile(zipFile,Charset.forName(alipayZipFileCharset));  
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>)zip.entries();  
        //循环对压缩包里的每一个文件进行解压       
        while(entries.hasMoreElements())  
        {  
            entry = entries.nextElement();  
            //构建压缩包中一个文件解压后保存的文件全路径  
            entryFilePath = unzipFilePath + File.separator + entry.getName();  
            //构建解压后保存的文件夹路径  
            index = entryFilePath.lastIndexOf(File.separator);  
            if (index != -1)  
            {  
                entryDirPath = entryFilePath.substring(0, index);  
            }  
            else  
            {  
                entryDirPath = "";  
            }             
            entryDir = new File(entryDirPath);  
            //如果文件夹路径不存在，则创建文件夹  
            if (!entryDir.exists() || !entryDir.isDirectory())  
            {  
                entryDir.mkdirs();  
            }  
              
            //创建解压文件  
            File entryFile = new File(entryFilePath);  
            fileList.add(entryFile);
            if (entryFile.exists())  
            {  
                //检测文件是否允许删除，如果不允许删除，将会抛出SecurityException  
                SecurityManager securityManager = new SecurityManager();  
                securityManager.checkDelete(entryFilePath);  
                //删除已存在的目标文件  
                entryFile.delete();   
            }  
              
            //写入文件  
            bos = new BufferedOutputStream(new FileOutputStream(entryFile));  
            bis = new BufferedInputStream(zip.getInputStream(entry));  
            while ((count = bis.read(buffer, 0, bufferSize)) != -1)  
            {  
                bos.write(buffer, 0, count);  
            }  
            bos.flush();  
            bos.close();              
        }  
        return fileList;
    }  
    public static void ZipContraFile(String zippath ,String outfilepath ,String filename) {
		try {
	        File file = new File(zippath);//压缩文件路径和文件名
	        File outFile = new File(outfilepath);//解压后路径和文件名
	        ZipFile zipFile = new ZipFile(file,Charset.forName(alipayZipFileCharset));
	        ZipEntry entry = zipFile.getEntry(filename);//所解压的文件名
	        InputStream input = zipFile.getInputStream(entry);
	        OutputStream output = new FileOutputStream(outFile);
	        int temp = 0;
	        while((temp = input.read()) != -1){
	            output.write(temp);
	        }
	        input.close();
	        output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
    /**  解压缩（压缩文件中包含多个文件）可代替上面的方法使用。
     * ZipInputStream类
     * 当我们需要解压缩多个文件的时候，ZipEntry就无法使用了，
     * 如果想操作更加复杂的压缩文件，我们就必须使用ZipInputStream类
     * */
    public static List<File> ZipContraMultiFile(String zippath ,String outzippath){
    	List<File> fileList = new ArrayList<File>();
    	try {
            File file = new File(zippath);
            ZipFile zipFile = new ZipFile(file,Charset.forName(alipayZipFileCharset));
            ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry = null;
            InputStream input = null;
            OutputStream output = null;
            while((entry = zipInput.getNextEntry()) != null){
                System.out.println("解压缩" + entry.getName() + "文件");
                File outFile = new File(outzippath + File.separator + entry.getName());
                fileList.add(outFile);
                if(!outFile.getParentFile().exists()){
                    outFile.getParentFile().mkdir();
                }
                if(!outFile.exists()){
                    outFile.createNewFile();
                }
                input = zipFile.getInputStream(entry);
                output = new FileOutputStream(outFile);
                int temp = 0;
                while((temp = input.read()) != -1){
                    output.write(temp);
                }
                input.close();
                output.close();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return fileList;
    }
    public static void main(String[] args) {
//	        String targetPath = "E:\\Win7壁纸";
//	        File file = ZipUtil.zip(targetPath);
//	        System.out.println(file);
        try {
//	        	ZipContraMultiFile("D:\\Apache\\apache-tomcat-7.0.69\\webapps\\upay-payment\\bill.zip","D:\\Apache\\apache-tomcat-7.0.69\\webapps\\upay-payment\\bill");
        	List<File> list = unZipFiles("D:\\Apache\\apache-tomcat-7.0.69\\webapps\\upay-payment\\upay-payment.zip","D:\\Apache\\apache-tomcat-7.0.69\\webapps\\upay-payment\\filelist\\");
        	System.out.println(list);
//	        	ZipUtil zip = new ZipUtil();
//	        	zip.compress("D:\\Workspaces\\123\\","D:\\Workspaces\\123.zip");
		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
    }

}
