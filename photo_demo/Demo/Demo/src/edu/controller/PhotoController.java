package edu.controller;

import edu.bean.Photo;
import edu.bean.User;
import edu.service.PageResult;
import edu.service.PhotoService;
import edu.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.jvm.hotspot.debugger.Page;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("mvcPhoto")
@RequestMapping("/photo")
public class PhotoController extends OutputSupportController{
    private PhotoService photoService;

    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Photo photo, HttpSession session,  HttpServletRequest request) throws IOException {
        User user = (User) session.getAttribute("user");
        if(user != null) {
            long now = System.currentTimeMillis();

            photo.setCreator(user.getId());
            photo.setCreateDt(new Timestamp(now));

            MultipartFile  mf = photo.getFile();
            if(mf != null){
                String uploadHome = request.getServletContext().getRealPath("/uploads");
                String pf = StringUtils.substringAfterLast(mf.getOriginalFilename(),".");
                String filePath =  user.getId() + "/" + photo.getCatalog();
                String dirPath =  uploadHome + "/" + filePath;
                String filename = now + "." + pf;
                File destDir = new File(dirPath);
                destDir.mkdirs();
                File destFile = new File(destDir,filename);
                photo.setFilePath(filePath + "/" + filename);
                FileCopyUtils.copy(mf.getBytes(),destFile);
                photo.setFile(null);
            }
            photoService.save(photo);
            return "redirect:/user/photoList.html";
        }
        else{
            return "redirect:/login.html";
        }
    }


    @RequestMapping(value = "/find",method = RequestMethod.GET)
    @ResponseBody
    public PageResult find(@RequestParam("catalog") String catalog, @RequestParam(name = "start",defaultValue = "0") int start, @RequestParam(name = "limit",defaultValue = "20") int limit, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user != null) {
            return photoService.findByCatalog(user.getId(),catalog,start,limit);
        }
        else{
            return new PageResult();
        }
    }

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public PageResult query(@RequestParam("catalog") String catalog,@RequestParam("query") String query, @RequestParam(name = "start",defaultValue = "0") int start, @RequestParam(name = "limit",defaultValue = "20") int limit, HttpSession session) {
        return null;
    }

    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    @ResponseBody
    public void remove(@RequestParam("id") int id, HttpServletRequest request, HttpSession session, HttpServletResponse response){
        User user = (User) session.getAttribute("user");
        Photo photo = photoService.getById(id);
        if(user == null || photo.getCreator() != user.getId()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        if(photo != null) {
            String uploadHome = request.getServletContext().getRealPath("/uploads");
            String filePath = photo.getFilePath();
            FileUtils.deleteQuietly(new File(uploadHome,filePath));
            photoService.remove(id);
        }
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public Photo remove(@RequestParam("id") int id, HttpServletResponse response, HttpSession session){
        User user = (User) session.getAttribute("user");
        Photo photo = photoService.getById(id);

        if(photo == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        if(user == null || photo.getCreator() != user.getId()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

        return photo;
    }

}
