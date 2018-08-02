package br.com.tfdonline.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	/*
	@RequestMapping(value = "/uploadform")
    public String showFormUpload(Model model) {
		
		return "uploadformpage";
	}
	
	
    //@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @RequestMapping(value = "/upload")
    public String handleFormUpload(@RequestParam(value= "name", required=false) String name,
        @RequestParam("file") MultipartFile multipartfile, HttpServletRequest request) {

        if (!multipartfile.isEmpty()) {
            try {
				byte[] bytes = multipartfile.getBytes();
				
				String ApplicationPath= request.getServletContext().getInitParameter("upload.directory");
				System.out.println("UploadDir"+ ApplicationPath);
				File f1 = new File(ApplicationPath+File.separator+multipartfile.getOriginalFilename());
				multipartfile.transferTo(f1);
				
				
				System.out.println("Passei pelo upload...");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            // store the bytes somewhere
           return "home";
       } else {
           return "home";
       }
    }
*/
}