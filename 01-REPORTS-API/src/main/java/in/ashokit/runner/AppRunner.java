package in.ashokit.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.EligibilityDetails;
import in.ashokit.repo.EligibilityDetailsRepo;
@Component
public class AppRunner implements ApplicationRunner{
	@Autowired
	private EligibilityDetailsRepo repo;
	
	public void run(ApplicationArguments args) throws Exception{
		EligibilityDetails entity1=new EligibilityDetails();
		entity1.setEligId(1);
		entity1.setName("John");
		entity1.setMobile(123456789L);
		entity1.setGender("M");
		entity1.setSsn(6868678L);
		entity1.setPlanName("SNAP");
		entity1.setPlanStatus("Approved");
		repo.save(entity1);
		EligibilityDetails entity2=new EligibilityDetails();
		entity2.setEligId(2);
		entity2.setName("JohnDoe");
		entity2.setMobile(1234560789L);
		entity2.setGender("F");
		entity2.setSsn(68686708L);
		entity2.setPlanName("ASNAP");
		entity2.setPlanStatus("Approved");
		repo.save(entity2);
		
		EligibilityDetails entity3=new EligibilityDetails();
		entity3.setEligId(3);
		entity3.setName("JohnJoe");
		entity3.setMobile(123424789L);
		entity3.setGender("M");
		entity3.setSsn(68686478L);
		entity3.setPlanName("wSNAP");
		entity3.setPlanStatus("Declined");
		repo.save(entity3);
		
	}
	

}
