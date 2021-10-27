package services;

import models.Reimbursement;
import models.ReimbursementStatus;
import models.User;
import repos.ReimbursementDAO;
import repos.ReimbursementDAOImpl;

import java.util.List;

public class ReimbursementService {
    private ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();

    public boolean setResolver(User user, Reimbursement reimbursement){
        if(user.getRole().getRoleId()<2){
            reimbursement.setResolver(user);
            reimbursementDAO.updateReimbursement(reimbursement);
            return true;
        }
        return false;
    }

    public boolean setStatus(Reimbursement reimbursement, int statusId){
        reimbursement.setResolved();
        reimbursement.setStatus(new ReimbursementStatus(statusId));
        return reimbursementDAO.updateReimbursement(reimbursement);
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
