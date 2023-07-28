package endpoints;

import static io.restassured.RestAssured.*;
import java.io.FileInputStream;
import java.util.Properties;
import io.restassured.response.Response;
import payloads.Users;


public class UsersAPI 
{
   Properties prop=new Properties();
   
   
   public Response createUser(Users user)
   {
	   try {
	   prop.load(new FileInputStream("src/main/java/conf/config.properties"));}catch(Exception e) {}
	   
	   Response resp=
	   given()
	      .header("Content-Type","application/json")
	      .header("Authorization","Bearer "+prop.getProperty("token"))
	      .body(user)
	   .when()
	       .post(routes.posturl);
	   
	   return resp;	   
   }
   public Response getUser(String id)
   {
	   Response resp=
	   given()
	      .header("Content-Type","application/json")    
	      .header("Authorization","Bearer "+prop.getProperty("token"))
	      .pathParam("userid", id)
	      
	   .when()
	       .get(routes.geturl);
	   
	   return resp;	   
   }
   public Response updateUser(Users user,String id)
   {
	   Response resp=
	   given()
	      .header("Content-Type","application/json")   
	      .header("Authorization","Bearer "+prop.getProperty("token"))
	      .pathParam("userid", id)
	      .body(user)
	   .when()
	       .patch(routes.updateurl);
	   
	   return resp;	   
   }
   public Response deleteUser(String id)
   {
	   Response resp=
	   given()
	      .header("Content-Type","application/json")   
	      .header("Authorization","Bearer "+prop.getProperty("token"))
	      .pathParam("userid", id)	      
	   .when()
	       .delete(routes.deleteurl);
	   
	   return resp;	   
   }
}
