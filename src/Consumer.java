
/**
 * 
 * @author vmngomezulu
 *
 */
public class Consumer {
	private char account;
	private String depositAmount;
    private String withdrawalAmount;
    
    public char getAccount() {
		return account;
	}
	public void setAccount(char account) {
		this.account = account;
	}
	public String getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}
	public String getWithdrawalAmount() {
		return withdrawalAmount;
	}
	public void setWithdrawalAmount(String withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}
}
