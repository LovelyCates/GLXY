package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * 实现 前端 模拟登录
 */
@RestController
@CrossOrigin  // 解决跨域
@RequestMapping("/eduservice/user")
public class EduLoginController {

    // login
    @PostMapping("/login")
    public R login(@RequestParam(required = false) String username, @RequestParam(required = false) String password){
        return R.ok().data("token", "admin");
    }

    // info
    @GetMapping("info")
    public R info(@RequestParam(required = false) String token){
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://api.lovelycat.top/api/v2/objects/icon/a220d034338ff14b.jpg");
    }

    // logout
    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
