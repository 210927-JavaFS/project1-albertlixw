package repos;

import models.ReimbursementStatus;
//import models.ReimbursementType;

import java.util.List;

public interface ReimbursementStatusDAO {
    List<ReimbursementStatus> findAllReimbursementStatus();
    ReimbursementStatus findById(int id);
    boolean addReimbursementStatus(ReimbursementStatus reimbursementStatus);
    boolean updateReimbursementStatus(ReimbursementStatus reimbursementStatus);
    boolean deleteReimbursementStatus(ReimbursementStatus reimbursementStatus);

}
