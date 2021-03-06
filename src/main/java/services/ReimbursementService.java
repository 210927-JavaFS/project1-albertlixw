package services;

import controllers.ReimbursementController;
import models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repos.ReimbursementDAO;
import repos.ReimbursementDAOImpl;
import repos.UserDAO;
import repos.UserDAOImpl;

import java.sql.Timestamp;
import java.util.List;

public class ReimbursementService {
    private final ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
    private final UserDAO userDAO = new UserDAOImpl();
    private static final Logger log = LoggerFactory.getLogger(ReimbursementController.class);

    public boolean resolveTicket(Reimbursement inputReimb){
        Reimbursement reimb = reimbursementDAO.findByReimbId(inputReimb.getReimbId());

        //get manager from inputReimb
        User manager = userDAO.findByUsername(inputReimb.getResolver().getUsername());
        System.out.println(manager);
        
        if(manager==null||manager.getRole()==null||(manager.getRole().getRoleId()<2)){
            System.out.println("manager role: " + manager.getRole());


            log.warn("Unauthorized user tried to resolve tickets. Timestamp :" + new Timestamp(System.currentTimeMillis()));
            return false;
        }

        //get statusId from inputReimb
        ReimbursementStatus status = new ReimbursementStatus(inputReimb.getStatus().getStatusId());
//        System.out.println("input status: " + status);

        reimb.setResolver(manager);
        reimb.setResolved();
        reimb.setStatus(status);
        
//        System.out.println("after resolved: " + reimb);
        log.info("Ticket resolved. reimbId: " + reimb.getReimbId());
        return reimbursementDAO.updateReimbursement(reimb);
    }

    public List<Reimbursement> findAllReimbursement(){
        return reimbursementDAO.findAllReimbursement();
    }

    public List<Reimbursement> findByReimbStatus(int statusId){

        return reimbursementDAO.findByReimbStatus(statusId);
    }

    public List<Reimbursement> findByReimbAuthorUsername(String username){

        return reimbursementDAO.findByReimbAuthorUsername(username);
    }

    public Reimbursement findByReimbId(int id){
        return reimbursementDAO.findByReimbId(id);
    }

    public boolean addReimbursement(Reimbursement reimbursement){
        User author = userDAO.findByUsername(reimbursement.getAuthor().getUsername());
        Reimbursement initReimb = new Reimbursement(reimbursement.getAmount(), reimbursement.getDescription(), author, new ReimbursementType(reimbursement.getType().getTypeId()));
        log.info("new reimbTicket created. " + initReimb);
        return reimbursementDAO.addReimbursement(initReimb);
    }
    public boolean updateReimbursement(Reimbursement reimbursement){
        log.info("A reimb has been updated: "+reimbursement);
        return reimbursementDAO.updateReimbursement(reimbursement);
    }
    public boolean deleteReimbursement(int id){
        log.info("reimbId: "+ id + " has been deleted");
        return reimbursementDAO.deleteReimbursement(findByReimbId(id));
    }

}
