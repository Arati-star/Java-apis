package in.ashokit.rest;

import java.util.ArrayList;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entty.plan;
import in.ashokit.service.PlanService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.ArrayList;
@RestController
public class PlanRestController {
	@Autowired
	private PlanService planservice;

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> Categories = planservice.getPlanCategories();
		return new ResponseEntity<>(Categories, HttpStatus.OK);

	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody plan plan) {
		boolean isSaved = planservice.savePlan(plan);
		String  responseMsg=" ";
		if (isSaved) {
			 responseMsg = "Plan Saved";
		} else {
			responseMsg = "Plan not  Saved";
		}
	
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<ArrayList<plan>> plans()
	{

ArrayList<plan> allPlans=(ArrayList<plan>) planservice.getAllPlans();
return new ResponseEntity<>(allPlans,HttpStatus.CREATED);
}
    @GetMapping("/plan/{planId}")
	public ResponseEntity<plan>  editPlan(@PathVariable Integer planId)
	{
		plan plan=planservice.getplanById(planId);
	    return new ResponseEntity<>(plan, HttpStatus.OK);
}
@PutMapping("/plan")
public ResponseEntity<String>updatePlan(@RequestBody plan plan)
{

	boolean isUpdated=planservice.updatePlan(plan);

    String msg="";
    if(isUpdated)
    {
    	msg="is updated";

   }
    else
    {
    	msg="not";
    }

    return new ResponseEntity<>(msg, HttpStatus.OK);
}

@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String>deletePlan(@PathVariable Integer planId)
	{

		boolean isDeleted=planservice.deletePlanById(planId);

        String msg="";
        if(isDeleted)
        {
        	msg="isdeleted";

	   }
        else
        {
        	msg="not";
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);
}
@PutMapping("/status-change/{planId}/{status}")
public ResponseEntity<String>statusChange(@PathVariable Integer planId,@PathVariable String status)
{
boolean isStatusChanged=planservice.planStatusChange(planId,status);
String msg="";
if(isStatusChanged)
{
	msg="ischanged";

}
else
{
	msg="not";
}

return new ResponseEntity<>(msg, HttpStatus.OK);
}
}
