package services;

import controllers.App;
import controllers.ReimbursementController;
import models.Reimbursement;
import models.ReimbursementStatus;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repos.ReimbursementDAO;
import repos.ReimbursementDAOImpl;

import java.sql.Timestamp;
import java.util.List;

public class ReimbursementService {
    private ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
    private static Logger log = LoggerFactory.getLogger(ReimbursementController.class);

    public boolean resolveTicket(User manager, Reimbursement reimb, int statusId){
        if(manager.getRole()==null||manager.getRole().getRoleId()<2){
            log.warn("Unauthorized user tried to resolve tickets. Timestamp :" + new Timestamp(System.currentTimeMillis()));
            return false;
        }
        reimb.setResolver(manager);
        reimb.setResolved();
        reimb.setStatus(new ReimbursementStatus(statusId));
        reimbursementDAO.updateReimbursement(reimb);
        return true;    
    }

    public List<Reimbursement> findAllReimbursement(){
        return reimbursementDAO.findAllReimbursement();
    }

    public Reimbursement findByReimbId(int id){
        return reimbursementDAO.findByReimbId(id);
    }

    public boolean addReimbursement(Reimbursement reimbursement){
        return reimbursementDAO.addReimbursement(reimbursement);
    }
    public boolean updateReimbursement(Reimbursement reimbursement){
        return reimbursementDAO.updateReimbursement(reimbursement);
    }
    public boolean deleteReimbursement(int id){
        return reimbursementDAO.deleteReimbursement(findByReimbId(id));
    }



}
