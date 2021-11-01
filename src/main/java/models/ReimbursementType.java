package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ReimbursementType {
    @Id
    private int typeId;
    @Column(updatable = false, nullable = false)
    private String type;

    public ReimbursementType(int typeId) {
        if(typeId == 1){
            this.type = "Lodging";
        }else if(typeId == 2){
            this.type = "Travel";
        }else if(typeId == 3){
            this.type = "Food";
        }else if(typeId == 4){
            this.type = "Other";
        }
        this.typeId = typeId;
    }

    public ReimbursementType(int typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }

    public ReimbursementType() {
        super();
    }
    @Override
    public String toString() {
        return "ReimbursementType{" +
                "typeId=" + typeId +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementType that = (ReimbursementType) o;
        return typeId == that.typeId && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, type);
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
