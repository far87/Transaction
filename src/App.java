
public class App {

	public static void main(String[] args) {
		
		AccountService as=new AccountServiceImpl();
		
		//as.transferMoney(102, 101, 50);
		try {
		as.insertAccount(102, "owner-3", 15, "2021-12-13",false);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
