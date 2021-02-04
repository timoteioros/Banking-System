package queries;

public class DepositQueries {
    public static final String DEPOSIT_BY_NAME = "Select d From Deposit d WHERE d.deposit_name= :deposit_name";
    public static final String DELETE_DEPOSIT = "DELETE From Deposit d WHERE d.iban = :iban";
    public static final String UPDATE_DEPOSIT = "UPDATE Deposit d Set d.balance = :balance WHERE d.iban = :iban";
    public static final String DEPOSIT_BY_IBAN = "Select d From Deposit d WHERE d.iban= :iban";

}
