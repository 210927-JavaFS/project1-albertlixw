package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ReimbursementStatus {
    @Id
    private int statusId;
    private String status;

    public ReimbursementStatus() {super();}
    public ReimbursementStatus(int statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReimbursementStatus{" +
                "statusId=" + statusId +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementStatus that = (ReimbursementStatus) o;
        return statusId == that.statusId && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, status);
    }


    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
