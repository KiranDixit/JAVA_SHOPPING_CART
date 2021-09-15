import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class menu{
	public static void main(String[] args) throws SQLException {
		asn u=new asn();
		Connection con = null;
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.println("1.Signup\n2.Login");
			System.out.println("Enter Your choice");
			int ch=sc.nextInt();
			
			con=u.dbconnect();
			switch(ch) {
			case 1:
				try {
					customer l=new customer();
					l.signup(con);
	
					}

				catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
					customer b=new customer();
					String a=b.login();
				//	System.out.println(".........................."+a);
					if(b.logc==1) {
					while(true) {
					
					System.out.println("\n\n\nItems Available are: \n ITEM\tPRODUCT_ID\tPRICE\nPen\t1000\t\t20\nPencil\t1001\t\t05\nBook\t1003\t\t40\n\n\n1.Add to cart\n2."
							+ "Delete from cart\n3.Display Cart\n4.Order Now \n5 View Orders\n6.Cancel Order\n7.Logout");
					System.out.println("Enter Your choice");
					int c=sc.nextInt();
		
					switch(c) {
					case 1:
						cart c1=new cart();
						c1.addcart(a);
						break;
					case 2:
						cart c2=new cart();
						c2.deletefromcart(a);
						break;
					case 3:
						cart c3=new cart();
						c3.displaycart(a);
						System.out.println("\n\n\n");
						break;
					case 4:
						
						orders o=new orders();
						o.placeorder(a);
						break;
					case 5:
						order_details oo=new order_details();
						oo.getdetails(a);
						break;
					case 6:
						order_details odt=new order_details();
						odt.cancelorder(a);
						break;
					
					
					    
						
					
					}
					}
					}

				
		
					
					
						
						
					}
					
					}
			
		
		
		
	}
}
	

