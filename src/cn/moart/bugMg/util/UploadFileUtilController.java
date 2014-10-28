package cn.moart.bugMg.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.moart.bugMg.bean.UploadFile;

@Controller
public class UploadFileUtilController {
	@RequestMapping(value = "fileUpload.shtml")
	@ResponseBody
	public String processImageUpload(UploadFile file22,
			HttpServletRequest request) {
		String callback = request.getParameter("CKEditorFuncNum");
		try {
			String path = "F:/ckeditor/fileUpload/aaa/";
			// 转型为MultipartHttpRequest：
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 获得文件：
			MultipartFile file = multipartRequest.getFile("upload");
			// 获得文件名：
			String filename = file.getOriginalFilename();

			InputStream input = file.getInputStream();
			File savePath = new File(path);
			if (!savePath.exists()) { // 文件夹
				savePath.mkdir();
			}
			
			String newfilename = initFileName(filename);
			FileOutputStream fos = new FileOutputStream(savePath.toString()
					+ newfilename);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = input.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			input.close();
			fos.close();

			return "<script type='text/javascript'>"
					+ "window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'" + path + "',''" + ")" + "</script>";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
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
