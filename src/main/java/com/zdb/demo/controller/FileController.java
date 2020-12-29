package com.zdb.demo.controller;

import com.zdb.demo.util.ResultCodeEnum;
import com.zdb.demo.util.ResultUtil;
import com.zdb.demo.util.UploadFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件操作
 *
*/
@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {

	@Value("${uploadPath}")
	private String uploadPath;

	/**
	 * 根据类型不同上传到不同的文件夹下
	 * SpringMVC 用的是 的MultipartFile来进行文件上传
	 * <p>
	 * 这里用@RequestParam()来指定上传文件为MultipartFile
	 * @param file
	 * @return
	 * @throws Exception
	 * @author xulf
	 * @time 2017年12月14日下午5:24:18
	 */

	@PostMapping("/uploadFile")
	@ResponseBody // 这里upfile是config.json中图片提交的表单名称
	public Map<String, Object> uploadFile(
			@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "dirName",required = false) String dirName) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 获取不同的渠道路径
		if (file == null || file.isEmpty()) {
			log.info("======================上传文件为空========================================");
			return ResultUtil.resultObject(ResultCodeEnum.FAIL.getValue(), "文件为空！").end();
		}
		dirName = dirName == null ? "" : "" + dirName;
		String path = UploadFileUtil.upload(uploadPath, file, dirName);
		// 是否上传成功
		// 文件原名称
		String fileName = file.getOriginalFilename();

		return ResultUtil.resultObject(ResultCodeEnum.SUCCESS.getValue())
				.appendDataToOut("rCode", "true") // 是否上传成功
				.appendDataToOut("original", fileName) // 文件原名称
				.appendDataToOut("type", fileName.substring(file.getOriginalFilename().lastIndexOf(".") + 1)) //文件类型 .+后缀名
				.appendDataToOut("url", path) // 路径
				.appendDataToOut("size", file.getSize() + "") // 文件大小（字节数）
				.end();
	}
	/**
	 * 下载文件
	 *
	 * @param fileUrl  fileUrl		文件的url从数据库中获取
	 * @RequestParam("dirName") String dirName 	文件夹名称，前台固定写好
	 * @RequestParam("id") Long id	下载界面的主ID
	 * @param response HttpServletResponse
	 */
	@GetMapping("/downloadFile")
	@ResponseBody
	public void downloadFile(
			@RequestParam("fileUrl") String fileUrl ,
			@RequestParam("fileName") String fileName,
			@RequestParam(required = false) String dirName,
			@RequestParam(required = false) Long id,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		File file = new File(fileUrl);
		String name = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
		response.setHeader("Content-Disposition", "attachment;filename=" + name);
		//文件下载类型--二进制文件
		response.setContentType("application/octet-stream");
		InputStream in = new FileInputStream(file);

		OutputStream out = response.getOutputStream();
		int buffSize = 1024;
		byte[] buff = new byte[buffSize];
		int read;
		while ((read = in.read(buff)) != -1) {
			out.write(buff, 0, read);
		}

		in.close();
		out.close();
	}


}
