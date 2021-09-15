import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class asn{
	Scanner sc=new Scanner(System.in);
	
	Connection dbconnect() 
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","kiran","Kiran@12345678");
			System.out.println("Connection Established Successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
	
	 class customer extends asn
	 {
		 int logc=0;
		
		public void signup(Connection con) throws SQLException 
		{
			
			System.out.println("Enter Username");
			String uname=sc.nextLine();
			System.out.println("Enter Email");
			String email=sc.nextLine();
			System.out.println("Enter Password");
			String pass=sc.nextLine();
			System.out.println("Enter Default shipping address");
			String address=sc.nextLine();
			
			
			//Here type should be matched
			// Error occured because I changed it to Integer
			
			try {
				Statement st = con.createStatement();
				String query = "insert into user(uname,email,pass,shipping_address) values('"+uname+"','"+email+"','"+pass+"','"+address+"');";
			
				st.execute(query);
				 PreparedStatement pr=con.prepareStatement("select id from user where email='"+email+"';");
				 ResultSet rs=pr.executeQuery();
				//String queryn = "select id from user where email='"+email+"';";
				// st.execute(queryn);
				 
				 while(rs.next())
			 		{
			 		System.out.println("\nYour User ID is   "+rs.getString(1)+"  Remember for future Login \n");
			 		}
			}
				
			catch(Exception e) 
			{
			e.printStackTrace();
			}
		
			
		}
	
	
	
		 
		 public String login() throws SQLException 
		 {
			Scanner sc=new Scanner(System.in);
			 Connection con=null;
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","kiran","Kiran@12345678");
			 System.out.println("Enter Userid");
			 String u=sc.nextLine();
			 System.out.println("Enter password");
			 String psw=sc.nextLine();
			 
			 Statement st=con.createStatement();
			 PreparedStatement pr=con.prepareStatement("select * from user");
			 ResultSet rs=pr.executeQuery();
			//Here type should be matched
				// Error occured because I changed it to Integer
	
			 	while(rs.next())
			 		{
			 			if(rs.getString("id").equals(u))
			 				{
			 					if(rs.getString("pass").equals(psw))
			 							{
			 								System.out.println("Logged in\n\n");
			 						
			 								 PreparedStatement pre=con.prepareStatement("select time from user where id="+u+";");
			 								ResultSet t=pre.executeQuery();
			 								while(t.next()) {
			 								
			 								System.out.println("LAST LOGIN OF USER "+t.getString(1));
			 								
			 								}
			 							
			 									logc+=1;
			 									
			 									DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			 									LocalDateTime now = LocalDateTime.now();
			 									String last=dtf.format(now);
		 									// con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","kiran","Kiran@12345678");
			 									String querya = "update user set time='"+last+"' where id="+u+";";
			 									st.execute(querya);
			 									break;
			 							}
			 					else
			 					{
			 							System.out.println("invalid username or password");
			 					}
		
			 				}
			 		
			 		}
			return u;	
		 }
	
	 }
		 
class cart extends customer{
	
	public void addcart(String uid) {
		 System.out.println("Enter Product id");
		 int pid=sc.nextInt();
		 if(pid==1000 || pid==1001 || pid==1003 ) {
		 
		try {
			String pname = "";
//Compulsory creation			
			if(pid==1000) {
			pname+="pen";
			}
			else if(pid==1001){
				pname+="pencil";
				
			}
			else if(pid==1002){
				pname+="Book";
			}
			String query = "insert into cart values('"+uid+"','"+pid+"','"+pname+"');";
			Connection con = null;
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","kiran","Kiran@12345678");
			Statement st = con.createStatement();
			st.execute(query);
			System.out.println("\nItem added to cart \n\n");
			
		
		}
		
		catch(Exception e) 
		{
		e.printStackTrace();
		}

		 }
		 else {
			 System.out.println("Enter Correct product ID");
		 }
	}
	
	public void deletefromcart(String uid){
		int check=0;
		 System.out.println("Enter Product id to be deleted");
		 String pidu=sc.nextLine();
		 try {
				Connection con = null;
				 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","kiran","Kiran@12345678");
			 PreparedStatement pra=con.prepareStatement("select pid from cart where uid="+uid+";");
			 ResultSet rs=pra.executeQuery();
			 	while(rs.next())
			 		{
			 		
			 		
			 			if(rs.getString("pid").equals(pidu)) {
			 				check+=1;
			 				
			 			}
			 		}
			//Compulsory creation	
			 
			 			if(check==1) {
						String queryd = "delete from cart where pid='"+pidu+"'and uid="+uid+";";
						 con = null;
						 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","kiran","Kiran@12345678");
						Statement st = con.createStatement();
						st.execute(queryd);
						System.out.println("Deleted from cart Successfully");
			 			}
			 			else {
			 				System.out.println("\n\nEnter valid productID\n\n");
			 			}
					}
					
					catch(Exception e) 
					{
					e.printStackTrace();
					}
		 
	}
	
	public void displaycart(String uid) throws SQLException {
		 Connection con=null;
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","kiran","Kiran@12345678");
		 PreparedStatement pr=con.prepareStatement("select pid,pname from cart where uid='"+uid+"';");
		 ResultSet rs=pr.executeQuery();
		 System.out.println("CART DETAILS \n-------------------------------------------------------------------------------------------\n");
		 	while(rs.next())
		 		{
		 		System.out.println("Product-ID "+rs.getString(1));
		 		System.out.println("Product-Name "+rs.getString(2)+"\n");
		 		}
		 	System.out.println("-------------------------------------------------------------------------------------------\\n");
				
		}

}
 class orders extends customer{
	 
	 
	public void placeorder(String uid){
		Scanner sc=new Scanner(System.in);
		int shipcost=40;
		System.out.println("Enter Product ID to be order");
		int pid=sc.nextInt();
		 if(pid==1000 || pid==1001 || pid==1003 ) {
		

		 int price=0;
		
		if(pid==1000) {

				price+=10;
			
		}
		else if(pid==1001) {
			 price+=5;
			
		}
		else if(pid==1003){
			 price+=40;
			
		}
		Scanner a=new Scanner(System.in);
		System.out.println("Shipping type\n1.Normal\n2.Prime");
		String shiptype=a.nextLine();
	
		Scanner sc1=new Scanner(System.in);
		System.out.println("Billing Address");
		String billing=sc1.nextLine();
		System.out.println("Enter quantity");
		int quantity=sc.nextInt();
	
		LocalDate odate = LocalDate.now();
		
	
		int total=price*quantity;
		total+=40;
		
	
	
		try {
		 Connection con = null;
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","kiran","Kiran@12345678");
		 
		
		 String que = "insert into orderdetails(pid,total,billaddress,shiptype,odate,uid)values('"+pid+"',"+total+",'"+billing+"','"+shiptype+"','"+odate+"',"+uid+");";
		 Statement st = con.createStatement();
			st.execute(que);
			System.out.println("\nItem ordered Successfully");
	
	 //PreparedStatement pr=con.prepareStatement("select shipping_address from user where id="+uid+";");
	// ResultSet rs=pr.executeQuery();
	 //rs.next();
	//shipaddress+=rs.getString(1);
		
	// System.out.println(shipaddress);
	}
	
	catch(Exception e) 
	{
	e.printStackTrace();
	}
		 }
		 else {
			 System.out.println("Enter Valid prouct ID");
		 }
	}

	
}
 
 
 class order_details extends orders{
	 
	 
	 
	 public void getdetails(String uid) throws SQLException
	 {
		 System.out.println("----------------------------------------------------------------------");
		 Connection con=null;
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","kiran","Kiran@12345678");
			 PreparedStatement pre=con.prepareStatement("select * from orderdetails where uid="+uid+";");
			ResultSet t=pre.executeQuery();
			while(t.next()) {
			
			System.out.println("\n\nOrder ID = "+t.getString(1));
			System.out.println("Product ID = "+t.getString(2));
			System.out.println("Shipping charge=40\nTotal Price = "+t.getString(3));
			System.out.println("Billing Address= "+t.getString(4));
			System.out.println("Shipping type = "+t.getString(5));
			System.out.println("Order Date = "+t.getString(6));
			System.out.println("User id = "+t.getString(3)+"\n\n");
			System.out.println("---------------------------------------------------------------------");
			
			
			} 
			
	 }
	 
	 
	 public void cancelorder(String uid) throws SQLException
	 {
		 Scanner b=new Scanner(System.in);
		 System.out.println("Enter Order Id to be cancel");
		 int od=b.nextInt();
		 Connection con = null;
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","kiran","Kiran@12345678");
		 String que = "delete from orderdetails where oid="+od+" and uid='"+uid+"';";
		 
		 //Here user id is taken because he may delete other's orders also
		 Statement st = con.createStatement();
			st.execute(que);
		 
	 }
 }
