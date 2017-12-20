package org.xwsx.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static String save(MultipartFile file,String savePath) throws IOException {

        if(file != null && file.getSize()>0){
            File fileFolder = new File(savePath);
            if(!fileFolder.exists()){
                fileFolder.mkdirs();
            }
            File saveFile = getFile(savePath,file.getOriginalFilename());
            file.transferTo(saveFile);
            return saveFile.getName();
        }
        return null;
    }
    public static File getFile(String savePath,String filePath){
        File file = new File(savePath+System.currentTimeMillis()+"_"+filePath);
        return file;
    }
    public static boolean delete(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            return false;
        }else{
            return file.delete();
        }
    }
}

