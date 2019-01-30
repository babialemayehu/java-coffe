package com.group2.util.support;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class FileUpload {
    DiskFileItemFactory factory;
    ServletFileUpload sfile;
    HttpServletRequest request;
    Properties config;
    String root;
    public FileUpload(HttpServletRequest request) throws IOException {
        this.config = new Properties();
        this.config.load(new FileInputStream("/root/IdeaProjects/Coffe-project/resource/db_config.properties"));

        factory = new DiskFileItemFactory();
        sfile = new ServletFileUpload(factory);
        root = this.config.getProperty("STORAGE_ROOT");
        this.request = request;
    }
    public ArrayList<File> uploadFiles(String dir){
        ArrayList<File> uploadedFiles = new ArrayList<>();
        try {
            List<FileItem> files = this.sfile.parseRequest(request);

            for(FileItem item: files){
                File file = new File(root+dir+item.getName());
                item.write(file);
                uploadedFiles.add(file);
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadedFiles;
    }
    public HashMap<String, Object> uploadFile(String dir, String inputName){
        File file;
        HashMap<String, Object> userInputs = new HashMap<>();
        try {
            List<FileItem> files = this.sfile.parseRequest(request);

            for(FileItem item: files){
                if (item.getFieldName().equals(inputName)) {
                    file = new File(root + dir + item.getName());
                    item.write(file);
                    userInputs.put(item.getFieldName(),dir + item.getName());
                }else{
                    userInputs.put(item.getFieldName(),item.getString());
                }
            }
            return userInputs;
        } catch (FileUploadException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
