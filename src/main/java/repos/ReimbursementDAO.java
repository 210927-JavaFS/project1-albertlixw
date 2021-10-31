package repos;

import models.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {
    List<Reimbursement> findAllReimbursement();
    Reimbursement findByReimbId(int reimbId);
    
    Reimbursement findByAuthorId(int userid);
    Reimbursement findByResolverId(int userid);
    Reimbursement findByType(int typeid);
    Reimbursement findByStatus(int statusid);


    boolean addReimbursement(Reimbursement reimbursement);
    boolean updateReimbursement(Reimbursement reimbursement);
    boolean deleteReimbursement(Reimbursement reimbursement);

}
