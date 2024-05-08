package com.example.demo4.controller;

import com.example.demo4.service.UserService;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNullApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.spi.DirStateFactory;

@RestController
@Api(tags = "用户管理相关接口")
public class UserController {
    @Autowired
    UserService userService;
    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/AllUsers")
    public DirStateFactory.Result getAllUsers(){
        List<User> userList = userService.list();
        if (!userList.isEmpty()){
            return Result.success(userList);
        }else {
            return Result.error("查询所有用户失败!");
        }
    }
    /**
     * 根据 id 查询指定用户
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public Result getUserById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }
    /**
     * 插入用户
     * @param user
     * @return
     */
    @PostMapping("/user")
    public Result addUser(@RequestBody User user){
        if (userService.save(user)){
            return Result.success("插入用户成功！");
        }
        else return Result.error("插入用户失败");
    }

    @PutMapping("/user")
    public Result updateUser(@RequestBody User user){
        if (userService.updateById(user)){
            return Result.success("更新用户成功！");
        }
        else return Result.error("更新用户失败");
    }
    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/user")
    public Result deleteUserById(Integer id){
        if (userService.removeById(id)){
            return Result.success("删除用户成功！");
        }
        else return Result.error("删除用户失败！");
    }
}