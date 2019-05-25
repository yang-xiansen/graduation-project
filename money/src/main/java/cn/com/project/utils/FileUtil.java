package cn.com.project.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

public class FileUtil {
    //静态方法：三个参数：文件的二进制，文件路径，文件名
    //通过该方法将在指定目录下添加指定文件    
    public static void fileupload(byte[] file, String filePath,
            String fileName) throws IOException {
        //目标目录      
        File targetfile = new File(filePath);
        // 判断路径是否存在，不存在则新创建一个
        if (!targetfile.exists()) {
            targetfile.mkdirs();
        }
        //二进制流写入        
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * 文件下载
     * @author 
     * @title: download
     * @date 2019-3-7 下午07:13:45
     * @param request
     * @return String
     */
    public static void download(HttpServletResponse res, String path) {
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        String name = "";
        try {
            name = java.net.URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        File file = new File(path);
        if (file.exists()) {
            res.setHeader("content-type", "application/octet-stream");
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition",
                "attachment;filename=" + name);
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                os = res.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
