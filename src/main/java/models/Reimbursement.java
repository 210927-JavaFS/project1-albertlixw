package models;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Reimbursement {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbId;
    private int amount = 0;
    private Timestamp submitted, resolved;
    //java date time, timestamp
    private String description;
    private Blob receipt; //stretch goal

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private int author;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private int resolver = 0;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "statusId")
    private int statusId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "typeId")
    private int typeId;

    

    public Reimbursement(int amount, String description, int author, int statusId, int typeId) {
        this.amount = amount;
        this.submitted = new Timestamp(System.currentTimeMillis());
//        this.resolved = null;
        this.description = description;
//        this.receipt = receipt;
        this.author = author;
//        this.resolver = 0;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public Reimbursement(int reimbId, int amount, Timestamp submitted, Timestamp resolved, String description, Blob receipt, int author, int resolver, int statusId, int typeId) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId=" + reimbId +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", receipt=" + receipt +
                ", author=" + author +
                ", resolver=" + resolver +
                ", statusId=" + statusId +
                ", typeId=" + typeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return reimbId == that.reimbId && amount == that.amount && author == that.author && resolver == that.resolver && statusId == that.statusId && typeId == that.typeId && Objects.equals(submitted, that.submitted) && Objects.equals(resolved, that.resolved) && Objects.equals(description, that.description) && Objects.equals(receipt, that.receipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbId, amount, submitted, resolved, description, receipt, author, resolver, statusId, typeId);
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getReceipt() {
        return receipt;
    }

    public void setReceipt(Blob receipt) {
        this.receipt = receipt;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getResolver() {
        return resolver;
    }

    public void setResolver(int resolver) {
        this.resolver = resolver;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
