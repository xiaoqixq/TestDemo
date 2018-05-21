package edu.controller;

import edu.bean.User;
import edu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller("mvcLogin")
public class LoginController extends OutputSupportController{
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login/on",method = RequestMethod.POST)
    @ResponseBody
    public Object doLogin(@RequestBody Map<String,Object> formData,HttpSession session){
       String loginName = (String) formData.get("loginName");
       String password = (String) formData.get("password");

        Map<String,Object> res = new HashMap<>();

       User user =  userService.getUserByLoginName(loginName);
       if(user != null && user.getPassword().equals(password)){
           session.setAttribute("user",user);
           res.put("code",200);
           String url = "";
           if(user.getRole().equals("user")){
               url = "user/photoList.html";
           }
           else{
               url = "admin/userList.html";
           }
           res.put("msg",url);
       }
       else{
           res.put("code",501);
           res.put("msg","用户不存在,或密码错误");
       }

       return res;
    }

    @RequestMapping("/admin/{pageName}.html")
    public String loadAdminPages(@PathVariable String pageName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null && !user.getRole().equals("admin")){
            return "redirect:/login.html";
        }
        else{
            String filename = request.getServletContext().getRealPath("admin/" + pageName + ".html");
            htmlFileOutput(response,new File(filename),isAcceptGzip(request));
            return null;
        }
    }

    @RequestMapping("/user/{pageName}.html")
    public String loadUserPages(@PathVariable String pageName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login.html";
        }
        else{
            String filename = request.getServletContext().getRealPath("user/" + pageName + ".html");
            htmlFileOutput(response,new File(filename),isAcceptGzip(request));
            return null;
        }

    }
}
