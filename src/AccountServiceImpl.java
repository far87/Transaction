
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.Driver;
/*Classe che gestisce la transazione utilizzando l'API JDBC*/
public class AccountServiceImpl implements AccountService{

	
	public void transferMoney(long accountSender, long accountReceiver, double amount)  {
		
		Connection connection=null;
		
		try {
			//DriverManager.registerDriver(new Driver());
			connection=DriverManager.getConnection("jdbc:h2:~/test", "", "");
			connection.setAutoCommit(false);
			
			Statement st=connection.createStatement();
			
			int control1=st.executeUpdate("update account set balance=balance-"+amount+ "where id="+accountSender);
			int control2=st.executeUpdate("update account set balance=balance+"+amount+ "where id="+accountReceiver);
			
			
//			if(control1==0 || control2==0) {
//				throw new SQLException();
//			}
			
			connection.commit();
			
		
		
		} 
		catch (SQLException e) {
			
			try {
				connection.rollback();
			} catch (SQLException e1) {

			}
			throw new RuntimeException(e);
		}
		
		finally {
			
			try {
			
				connection.close();
			
			} 
			catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
	}

	public void insertAccount(long id,String owner, double balance,String time, boolean locked) {
		Connection connection=null;
		
		try {
			connection=DriverManager.getConnection("jdbc:h2:~/test", "", "");
			Statement st=connection.createStatement();
			connection.setAutoCommit(false);
			st.execute("insert into account values("+id+","+"'"+owner+"',"+""+balance+","+"parsedatetime('"+time+"','yyyy-mm-dd')"+","+locked+")");
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} 
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
			
		}
		
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	
}
