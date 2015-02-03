package cn.moart.bugMg.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.moart.bugMg.bean.UploadFile;

@Controller
public class UploadFileUtilController {
	@RequestMapping("fileUpload.shtml")
	@ResponseBody
	public String processImageUpload(UploadFile file, HttpServletRequest request)
			throws IOException {
		String callback = request.getParameter("CKEditorFuncNum");
		// 如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		// 如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		// 并且上传多个文件时，前台表单中的所有<input
		// type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
		String folder = getCurrDate("yyyyMMdd");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/static/uploadedimages/"+folder);
		File f_dir= new File(realPath);
		if(!f_dir.exists()){
			f_dir.mkdirs();
		}
		String filename = initFileName(file.getUpload().getOriginalFilename());
		if (file.getUpload().isEmpty()) {
			System.out.println("file not upload");
		} else {
			//the file will upload to \\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\, if you use tomcat server
			//Don't care IO Stream closed,because the method FileUtils.copyInputStreamToFile() will close IO Stream
			FileUtils.copyInputStreamToFile(file.getUpload().getInputStream(),
					new File(realPath, filename));
		}
		String _url = "http://192.168.1.99:8080/" + realPath.substring(realPath.indexOf("static")).replace("\\", "/")+"/"+filename;
		return "<script type='text/javascript'>"
				+ "window.parent.CKEDITOR.tools.callFunction(" + callback
				+ ",'" + (_url)
				+ "',''" + ")" + "</script>";
	}
	
	public static String initFileName(String filename) {
		int l = filename.length();
		int c = filename.lastIndexOf(".");
		String ftype = filename.substring(c, l);
		return getCurrDate("yyyyMMddHHmmssSSS") + ftype;
	}
	
	public static String getCurrDate(String format) {
		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");// ETC/GMT-8
		TimeZone.setDefault(tz);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
