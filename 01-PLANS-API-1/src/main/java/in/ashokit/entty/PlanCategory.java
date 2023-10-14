package in.ashokit.entty;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Data
@Table(name="PLAN_CATEGORY")
public class PlanCategory {
	@Id
	@GeneratedValue
	@Column(name="CATEGORY_ID")
private Integer categoryId;
	@Column(name="CATEGORY_NAME")
private String categoryName;
	@Column(name="ACTIVE_SW")
private String activeSw;
	@Column(name="CREATED_BY")
private String createdBy;
	@Column(name="UPDATED_BY")
private String updatedBy;
	@Column(name="CREATE_DATE",updatable=false)
	@CreationTimestamp
private LocalDate createDate;
	@Column(name="UPDATE_DATE",insertable=false)
	@UpdateTimestamp
private LocalDate updaateDate;
	public Integer getCategoryID() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getCategoryName() {
		// TODO Auto-generated method stub
		return null;
	}

}
