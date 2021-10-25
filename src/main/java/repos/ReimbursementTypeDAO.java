package repos;

import models.ReimbursementType;

import java.util.List;

public interface ReimbursementTypeDAO {
    List<ReimbursementType> findAllReimbursementType();
    ReimbursementType findById(int id);
    boolean addReimbursementType(ReimbursementType reimbursementType);
    boolean updateReimbursementType(ReimbursementType reimbursementType);
    boolean deleteReimbursementType(ReimbursementType reimbursementType);
}
