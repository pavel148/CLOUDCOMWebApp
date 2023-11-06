package korobkin.CLOUDCOM.controller;

import korobkin.CLOUDCOM.model.Role;
import korobkin.CLOUDCOM.model.User;
import korobkin.CLOUDCOM.model.UserRole;
import korobkin.CLOUDCOM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    //создаение пользователя
    @Autowired
    private UserService userService;
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");
        Set < UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add( userRole);

            return this.userService.createUser(user,roles);
    }
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
            return this.userService.getUser(username);
    }

    //удаление по id
    @DeleteMapping("/userId")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);

    }



}
