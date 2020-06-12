package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.exception.GunsExceptionEnum;
import com.stylefeng.guns.core.exception.GunsRestExceptionEnum;
import com.stylefeng.guns.core.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Api("上传/获取图片")
@RestController
@RequestMapping("/restApi")
@Slf4j
public class UploadController {

	/**
	 * 图片上传接口
	 * @param picture
	 * @return
	 */
	@PostMapping("/auth/uploadImg")
	public ResponseEntity<?> upload(@RequestPart("file") MultipartFile picture,String type){
	        String pictureName = DateUtil.getDays() + "/" +UUID.randomUUID().toString() + "."+picture.getContentType().substring(picture.getContentType().lastIndexOf("/")+1);
	        try {
	            String fileSavePath =  GunsProperties.WEB_SERVER_PATH + "/web-imgs/" + pictureName;
				File saveFile =  new File(fileSavePath);
	            if(!saveFile.getParentFile().exists())saveFile.getParentFile().mkdirs();
	            picture.transferTo(saveFile);
	        } catch (Exception e) {
	        	log.error("图片上传失败",e);
	            throw new GunsException(GunsRestExceptionEnum.UPLOAD_ERROR);
	        }
	        return ResponseEntity.ok(pictureName);
	}

}
