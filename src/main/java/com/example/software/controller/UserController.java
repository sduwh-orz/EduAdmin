package com.example.software.controller;

import com.example.software.pojo.User;
import com.example.software.response.Response;
import com.example.software.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login")
    public synchronized Response login(@RequestParam String userId, @RequestParam String userPassword, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            return new Response(false, "已登录过.", null);
        } else {
            User user = userService.getUser(userId);
            if (user == null) {
                return new Response(false, "用户号错误.", null);
            } else {
                if (!user.getUserPassword().equals(userPassword)) {
                    return new Response(false, "密码错误.", null);
                } else {
                    httpSession.setAttribute("user", user.getUserId());
                    return new Response(true, "登录成功.", user);
                }
            }
        }
    }

    @GetMapping(path = "logout")
    public synchronized Response logout(HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            httpSession.removeAttribute("user");
            return new Response(true, "成功退出登录.", null);
        } else {
            return new Response(false, "尚未登录.", null);
        }
    }

    @GetMapping(path = "/getUserName/{userId}")
    public synchronized Response getUserName(@PathVariable(name = "userId") String userId) {
        if (userService.getUser(userId) == null) {
            return new Response(false, "用户不存在", null);
        }
        return new Response(true, "", userService.getUser(userId).getUserName());
    }
}
