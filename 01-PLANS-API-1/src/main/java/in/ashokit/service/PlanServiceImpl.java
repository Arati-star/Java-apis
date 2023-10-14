package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entty.PlanCategory;
import in.ashokit.entty.plan;
import in.ashokit.repo.PlanCategoryRepo;
import in.ashokit.repo.PlanRepo;
@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanRepo planRepo;
	@Autowired
	private PlanCategoryRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		// TODO Auto-generated method stub
		Map<Integer,String> categoryMap=new HashMap<>();
		List<PlanCategory> categories=planCategoryRepo.findAll();
	
		categories.forEach(category->{
			categoryMap.put(category.getCategoryID(),category.getCategoryName());
			
		});
		return categoryMap;
	}

	@Override
	public boolean savePlan(plan plan) {
		// TODO Auto-generated method stub
		plan saved=planRepo.save(plan);
		if(saved.getPlanId()!=null)
		{
		return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public List<plan> getAllPlans() {
		// TODO Auto-generated method stub
		return planRepo.findAll();
		
	}

	@Override
	public plan getplanById(Integer planId) {
		Optional<plan> findById=planRepo.findById(planId);
		if(findById.isPresent())
		{
			return findById.get();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePlan(plan plan) {
		// TODO Auto-generated method stub
		plan save=planRepo.save(plan);
		return plan.getPlanId()!=null;
		
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
		planRepo.deleteById(planId);
		status=true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		// TODO Auto-generated method stub
		Optional<plan> findById=planRepo.findById(planId);
		if(findById.isPresent())
		{
			plan plan=findById.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
		}
		return false;
	}

}
