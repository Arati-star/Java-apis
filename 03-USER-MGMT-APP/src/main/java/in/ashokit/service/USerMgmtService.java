package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.ActivateAccount;
import in.ashokit.binding.Login;
import in.ashokit.binding.User;

public interface USerMgmtService {
	public boolean saveUser(User user);
	public boolean activateUserAcc(ActivateAccount activateAccount);
	public List<User> getAllUsers();
	public User getUserById(Integer userId);
	public boolean deleteUserById(Integer userId);
	public boolean changeAccountStatus(Integer userId,String accStatus);
	public String login(Login login);
	public String forgotPwd(String email);
	
	

}
 