package controllers;

import io.javalin.Javalin;

public interface Controller {

    public void addRoutes(Javalin app);

}
