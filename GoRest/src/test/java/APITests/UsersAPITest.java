package APITests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import endpoints.UsersAPI;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.Users;

public class UsersAPITest extends BaseTest
{

	UsersAPI usersapi=new UsersAPI();
	Users user=new Users();
	String username,emailid,gender="male",status="active";
	String userid;

	@BeforeClass
	public void generateuserdata()
	{
		username=faker.name().fullName();
		emailid=faker.internet().safeEmailAddress();

		user.setName(username);
		user.setEmail(emailid);
		user.setGender(gender);
		user.setStatus(status);
	}

	@Test(priority=1)
	public void testCreateUser()
	{
		Response resp=usersapi.createUser(user);
		JsonPath path=new JsonPath(resp.asString());
		userid= path.get("id").toString();
		
		test=report.createTest("CreateUserAPI");
		if(resp.getStatusCode()==201)
			test.log(Status.PASS,"The Response status code is 201");
		else
			test.log(Status.FAIL,"The Response status code is NOT 201");  
		if(userid!="")
			test.log(Status.PASS,"New userid is generated for Create user");
		else
			test.log(Status.FAIL,"New userid is not generated for create user");

		if(path.getString("name").matches(username))
			test.log(Status.PASS,"username created is correct");
		else
			test.log(Status.FAIL,"username created is not correct"); 

	}
	@Test(priority=2)
	public void testgetUser()
	{
		Response resp=usersapi.getUser(userid);

		test=report.createTest("GetUserAPI");
		if(resp.getStatusCode()==200)
			test.log(Status.PASS,"The Response status code is 200");
		else
			test.log(Status.FAIL,"The Response status code is NOT 200");  

	}
	@Test(priority=3)
	public void testupdateUser()
	{
		emailid=faker.internet().safeEmailAddress();
		user.setEmail(emailid);

		Response resp=usersapi.updateUser(user,userid);

		test=report.createTest("UpdateUserAPI");
		if(resp.getStatusCode()==200)
			test.log(Status.PASS,"The Response status code is 200");
		else
			test.log(Status.FAIL,"The Response status code is NOT 200");  

	}
	@Test(priority=4)
	public void testdeleteUser()
	{

		Response resp=usersapi.deleteUser(userid);

		test=report.createTest("DeleteUserAPI");
		if(resp.getStatusCode()==200)
			test.log(Status.PASS,"The Response status code is 200");
		else
			test.log(Status.FAIL,"The Response status code is NOT 200");  
	}
}
