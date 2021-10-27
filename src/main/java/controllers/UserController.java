package controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import models.User;
import models.UserDTO;
import services.LoginService;
import services.UserService;

import java.util.List;

public class UserController implements Controller{
    private UserService userService = new UserService();
    private LoginService loginService = new LoginService();
    public Handler getAllUsers = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            List<User> list = userService.findAllUsers();

            ctx.json(list);
            ctx.status(200);
        }else{
            ctx.status(401);
        }

    };

    public Handler getUser = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            try{
                String idString = ctx.pathParam("user");
                int id = Integer.parseInt(idString);
                User user = userService.findById(id);
                ctx.json(user);
                ctx.status(200);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                ctx.status(406);
            }
        }else{
            ctx.status(401);
        }
    };

    public Handler addUser = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            User user = ctx.bodyAsClass(User.class);
            if(userService.addUser(user)){
                ctx.status(201);
            }else{
                ctx.status(400);
            }

        }else{
            ctx.status(401);
        }
    };

    public Handler updateUser = (ctx) -> {
        User user = ctx.bodyAsClass(User.class);
        if(userService.updateUser(user)){
            ctx.status(200);

        }else{
            ctx.status(400);
        }
    };

    public Handler deleteUser = (ctx) -> {
        String id = ctx.pathParam("user");
        try{
            int user = Integer.parseInt(id);
            if(userService.deleteUser(user)){
                ctx.status(200);
            }
            else{
                ctx.status(400);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ctx.status();
        }
    };

    private Handler loginAttempt = (ctx) -> {
        UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
        if(loginService.login(userDTO)){
            ctx.req.getSession();
            ctx.status(200);
        } else{
            ctx.req.getSession().invalidate();
          ctx.status(401);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/users", this.getAllUsers);
        app.get("/users/:user", this.getUser);
        app.post("/users", this.addUser);
        app.put("users", this.updateUser);
        app.delete("users/:user", this.deleteUser);
        app.post("/login", this.loginAttempt);
    }
}
