package repos;

import models.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {
    List<Reimbursement> findAllReimbursement();
    Reimbursement findByReimbId(int reimbId);
    boolean addReimbursement(Reimbursement reimbursement);
    boolean updateReimbursement(Reimbursement reimbursement);
    boolean deleteReimbursement(Reimbursement reimbursement);
}
