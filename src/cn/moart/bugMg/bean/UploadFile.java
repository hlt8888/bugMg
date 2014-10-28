package cn.moart.bugMg.bean;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadFile {
	private String name;
    private CommonsMultipartFile upload;
    private String filePath;
 
    public CommonsMultipartFile getUpload() {
        return upload;
    }
    public void setUpload(CommonsMultipartFile uploadFile) {
        this.upload = uploadFile;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
