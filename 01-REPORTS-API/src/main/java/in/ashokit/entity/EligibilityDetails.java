package in.ashokit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ELIGIBILITY_DETAILS")
@Data
@Setter
@Getter
@ToString
public class EligibilityDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eligId;

	private String name;
	private Long mobile;
	private String email;
	private String gender;
	private Long ssn;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	@CreationTimestamp
	private LocalDate createDate;
	@UpdateTimestamp
	private LocalDate updateDate;
	private String createdBy;
	private String updatedBy;


}
