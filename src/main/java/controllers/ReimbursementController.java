package controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import models.Reimbursement;
import services.ReimbursementService;

import java.util.List;

public class ReimbursementController implements Controller{
    private ReimbursementService reimbursementService = new ReimbursementService();

    public Handler getAllReimbursements = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            List<Reimbursement> list = reimbursementService.findAllReimbursement();
            ctx.json(list);
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    };

    public Handler getReimbursement = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
             try{
                 String idString = ctx.pathParam("reimbursement");
                 int id = Integer.parseInt(idString);
                 Reimbursement reimbursement = reimbursementService.findByReimbId(id);
                 ctx.json(reimbursement);
                 ctx.status(200);
             } catch (NumberFormatException e) {
                 e.printStackTrace();
                 ctx.status(406);
             }
        }else{
             ctx.status(401);
        }
    };

    public Handler addReimbursement = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
            if(reimbursementService.addReimbursement(reimbursement)){
                ctx.status(201);
            }else{
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    public Handler updateReimbursement = (ctx) -> {
        Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
        if(reimbursementService.updateReimbursement(reimbursement)){
            ctx.status(200);
        }else {
            ctx.status(400);
        }
    };

    public Handler deleteReimbursement = (ctx) -> {
        String id = ctx.pathParam("reimbursement");
        try{
            int reimbursement = Integer.parseInt(id);
            if(reimbursementService.deleteReimbursement(reimbursement)){
                ctx.status(200);
            }else{
                ctx.status(400);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ctx.status();
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/reimbursements", this.getAllReimbursements);
        app.get("/reimbursements/reimbursement", this.getReimbursement);
        app.post("/reimbursements", this.addReimbursement);
        app.put("/reimbursements", this.updateReimbursement);
        app.delete("/reimbursements/:reimbursement", this.deleteReimbursement);


    }
}
