package com.atguigu.gmall.util;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Description: json文件静态化工具类
 * ClassName: JsonStaticizeUtil
 * date: 2019/11/23 16:17
 *
 * @author fancy Email:395765197@qq.com
 * @version 1.0
 * @since JDK 1.8
 */
public class JsonStaticizeUtil {

    /**
     * 创建json静态化文件
     * @param newFileName 文件名
     * @param newFilePath 文件生成路径
     * @param newFileContent 文件内容
     */
    public static void createSaleAttrValueIdMappingSkuIdHashStaticizeFile(String newFileName, String newFilePath,String newFileContent) {
        if (!StringUtils.isEmpty(newFileContent)) {
            //创建一个Flie对象
            File file = new File(newFilePath+newFileName);

            //判断文件夹是否存在
            File parent = file.getParentFile();
            if (!parent.exists()) {
                //不存在则创建
                parent.mkdirs();
            }

            //声明一个文件输出流对象
            FileOutputStream outputStream = null;
            try {
                //创建一个文件输出流
                outputStream = new FileOutputStream(file);

                //写入数据到文件
                outputStream.write(newFileContent.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    //关闭流
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 移除json静态化文件
     *
     * @param filePath
     * @param fileName
     */
    public static void deleteSaleAttrValueIdMappingSkuIdHashStaticizeFile(String filePath,String fileName) {
        //创建一个Flie对象
        File file = new File(filePath+fileName);
        file.delete();
    }
}
