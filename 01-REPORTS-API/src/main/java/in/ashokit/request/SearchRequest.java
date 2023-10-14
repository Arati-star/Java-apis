package in.ashokit.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {
	 private String planName;
	 private String planStatus;
	 private LocalDate planStartDate;
	 private LocalDate planEndDate;
	 private LocalDate createDate;
	public String getPlanName() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getPlanStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	public LocalDate getPlanStartDate() {
		// TODO Auto-generated method stub
		return null;
	}
	public LocalDate getPlanEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

}
