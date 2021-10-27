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
    private User author;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User resolver;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "statusId")
    private ReimbursementStatus status;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "typeId")
    private ReimbursementType type;

    public Reimbursement(int amount, String description, User author, ReimbursementType type) {
        this.amount = amount;
        setSubmitted();
        this.description = description;
        this.author = author;
        this.type = type;
        this.status = new ReimbursementStatus(1, "submitted");
    }

    public Reimbursement(int amount, Timestamp submitted, Timestamp resolved, String description, Blob receipt, User author, ReimbursementStatus status, ReimbursementType type) {
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.status = status;
        this.type = type;
    }

    public Reimbursement(int reimbId, int amount, Timestamp submitted, Timestamp resolved, String description, Blob receipt, User author, User resolver, ReimbursementStatus status, ReimbursementType type) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
    }

    public Reimbursement() {
        super();
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
                ", status=" + status +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return reimbId == that.reimbId && amount == that.amount && Objects.equals(submitted, that.submitted) && Objects.equals(resolved, that.resolved) && Objects.equals(description, that.description) && Objects.equals(receipt, that.receipt) && Objects.equals(author, that.author) && Objects.equals(resolver, that.resolver) && Objects.equals(status, that.status) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbId, amount, submitted, resolved, description, receipt, author, resolver, status, type);
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
    public void setSubmitted() {
        this.submitted = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }
    public void setResolved() {
        this.resolved = new Timestamp(System.currentTimeMillis());
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public ReimbursementStatus getStatus() {
        return status;
    }

    public void setStatus(ReimbursementStatus status) {
        this.status = status;
    }

    public ReimbursementType getType() {
        return type;
    }

    public void setType(ReimbursementType type) {
        this.type = type;
    }

}
