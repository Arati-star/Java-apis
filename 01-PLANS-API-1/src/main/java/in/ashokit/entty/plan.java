package in.ashokit.entty;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="PLAN_MASTER")
public class plan {
	@Id
	@GeneratedValue
	@Column(name="PLAN_ID")
    private Integer planId;
	@Column(name="PLAN_NAME")
	private Integer planNAme;
	@Column(name="PLAN_START_DATE")
	private LocalDate planStartDate;
	@Column(name="PLAN_END_DATE")
	private LocalDate planEndDate;
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
	@Column(name="PLAN_CATEGORY_ID")
    private Integer planCategoryId;
	public Object getPlanId() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setPlanId(Integer planId2) {
		// TODO Auto-generated method stub
		
	}
	public void setActiveSw(String status) {
		// TODO Auto-generated method stub
		
	}
	
}
