package in.ashokit.service;

import java.util.List;
import java.util.Map;

import in.ashokit.entty.plan;

public interface PlanService {
	public Map<Integer,String> getPlanCategories();
	public boolean savePlan(plan plan);
	public List<plan> getAllPlans();
	public plan getplanById(Integer planId);
	public boolean updatePlan(plan plan);
	public boolean deletePlanById(Integer planId);
	public boolean planStatusChange(Integer planId,String status);
}
