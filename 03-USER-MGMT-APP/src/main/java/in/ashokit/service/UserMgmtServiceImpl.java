package in.ashokit.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.binding.ActivateAccount;
import in.ashokit.binding.Login;
import in.ashokit.binding.User;
import in.ashokit.entity.UserMaster;
import in.ashokit.repo.UserMasterRepo;
import in.ashokit.utils.EmailUtils;
@Service
public class UserMgmtServiceImpl implements USerMgmtService {
	@Autowired
	private UserMasterRepo userMasterRepo;
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		UserMaster entity=new UserMaster();
	    BeanUtils.copyProperties(user, entity);
		entity.setPassword(generateRandomPwd());
		entity.setAccStatus("In-Active");
		UserMaster save= userMasterRepo.save(entity);
		String subject="Your registration successful";
		String filename="REG-EMAIL-BODY.txt";
		String body;
		try {
			body = readEmailBody(entity.getFullname(),entity.getPassword(),filename);
			emailUtils.sendEmail(user.getEmail(), subject, body);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return save.getUserId()!= null;
	}

	@Override
	public boolean activateUserAcc(ActivateAccount activateAccount) {
		// TODO Auto-generated method stub
		UserMaster entity=new UserMaster();
		entity.setEmail(activateAccount.getEmail());
		entity.setPassword(activateAccount.getTempPwd());
		Example<UserMaster> of = Example.of(entity);
		List<UserMaster> findAll = userMasterRepo.findAll(of);
		if(findAll.isEmpty()) {
			return false;	
		}
		else
		{
			UserMaster userMaster=findAll.get(0);
			userMaster.setPassword(activateAccount.getNewPwd());
			userMaster.setAccStatus("Active");
			userMasterRepo.save(userMaster);
			return true;
		}
			
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<UserMaster> findAll = userMasterRepo.findAll();
		List<User> users=new ArrayList();
		for(UserMaster entity:findAll)
		{
			User user=new User();
			BeanUtils.copyProperties(entity, user);
			users.add(user);
		}
		return users;
	}

	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		Optional<UserMaster> findById = userMasterRepo.findById(userId);
		if(findById.isPresent())
		{
			User user=new User();
			UserMaster userMaster=findById.get();
			BeanUtils.copyProperties(userMaster, user);
			return user;
			
		}
		return null;
	}

	@Override
	public boolean deleteUserById(Integer userId) {
		// TODO Auto-generated method stub
		try {
		userMasterRepo.deleteById(userId);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public boolean changeAccountStatus(Integer userId, String accStatus) {
		// TODO Auto-generated method stub
		Optional<UserMaster> findById = userMasterRepo.findById(userId);
		if(findById.isPresent())
		{
			UserMaster userMaster = findById.get();
			userMaster.setAccStatus(accStatus);
			userMasterRepo.save(userMaster);
			return true;
		}
		return false;
	}

	@Override
	public String login(Login login) {
		// TODO Auto-generated method stub
		UserMaster entity=userMasterRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());

		if(entity==null)
		{
			return "Invalid credentials";
			
		}
		else
		{

			if(entity.getAccStatus().equals("Active"))
			{
				return "SUCCESS";
				
			}
			else {
				return "account not activated";
			}
		}
		
	}

	@Override
	public String forgotPwd(String email) {
		// TODO Auto-generated method stub
		UserMaster entity=userMasterRepo.findByEmail(email);
		if(entity==null)
		{
			return "Invalid email";
			
		}
		String subject= "Forgot Password";
		String filename="RECOVER-PWD-BODY.txt";
		try {
			String body=readEmailBody(entity.getFullname(),entity.getPassword(),filename);
			boolean sendEmail=emailUtils.sendEmail(email, subject, body);
			if(sendEmail)
			{
				return "PAssword sent to your registered email";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	private String generateRandomPwd()
	{
	        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		    String numbers = "0123456789";

		    // combine all strings
		    String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

		    // create random string builder
		    StringBuilder sb = new StringBuilder();

		    // create an object of Random class
		    Random random = new Random();

		    // specify length of random string
		    int length = 10;

		    for(int i = 0; i < length; i++) {

		      // generate random index number
		      int index = random.nextInt(alphaNumeric.length());

		      // get character specified by index
		      // from the string
		      char randomChar = alphaNumeric.charAt(index);

		      // append the character to string builder
		      sb.append(randomChar);
		    }

		    return  sb.toString();

		  }
	private String readEmailBody(String fullname,String pwd,String filename) throws FileNotFoundException{
		
		String url="";
		String mailBody=null;
		
		try {
			FileReader fr=new FileReader(filename);
			BufferedReader br=new BufferedReader(fr);
			StringBuffer buffer =new StringBuffer();
			String line = br.readLine();
			while(line!=null)
			{
				buffer.append(line);
				line=br.readLine();
			}
			br.close();
			mailBody=buffer.toString();
			mailBody=mailBody.replace("{FULLNAME}",fullname);
			mailBody=mailBody.replace("{TEMP-PWD}",pwd);
			mailBody=mailBody.replace("{URL}",url);
			mailBody=mailBody.replace("PWD",pwd);
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return mailBody;
		
	}
		}


