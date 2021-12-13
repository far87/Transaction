
public interface AccountService {

	public void transferMoney(long accountSender,long accountReceiver,double amount);
	public void insertAccount(long id,String owner,double balance,String time,boolean locked);
}
