package controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import models.Reimbursement;
import models.User;
import services.ReimbursementService;

import java.util.List;

public class ReimbursementController implements Controller{
    private ReimbursementService reimbursementService = new ReimbursementService();

    //TODO: get curr manager logged in
    //TODO: get Reimb from input
    //TODO: get statusId from input
//    public Handler resolveReimb = (ctx) -> {
//         if(ctx.req.getSession(false)!=null){
//             Reimbursement reimb = ctx.bodyAsClass(Reimbursement.class);
//             User manager = ctx.bodyAsClass(User.class);
//             int statusId = ctx.bodyAsClass(Integer.class);
//             if(reimbursementService.resolveTicket(manager, reimb, statusId)){
//                ctx.status(200);
//             }else{
//                 ctx.status(400);
//             }
//         }else{
//             ctx.status(401);
//         }
//    };

    public Handler updateReimb = (ctx) -> {
        Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
        if(reimbursementService.updateReimbursement(reimbursement)){
            ctx.status(200);
        }else {
            ctx.status(400);
        }
    };


    public Handler getAllReimbs = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            List<Reimbursement> list = reimbursementService.findAllReimbursement();
            ctx.json(list);
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    };

    //TODO: USE THIS TO GET Reimb
    public Handler getReimb = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
             try{
                 String idString = ctx.pathParam("reimb");
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

    //TODO: USE THIS to add reimb
    public Handler addReimb = (ctx) -> {
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
    



    public Handler deleteReimb = (ctx) -> {
        String id = ctx.pathParam("reimb");
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
        app.get("/reimbs", this.getAllReimbs);
        app.get("/reimbs/:reimb", this.getReimb);
        app.post("/reimbs", this.addReimb);
        app.put("/reimbs", this.updateReimb);
        app.delete("/reimbs/:reimb", this.deleteReimb);

    }
}
