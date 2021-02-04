package queries;

public class UserQueries {

    public static final String USER_BY_USERNAME_QUERY = "Select u From User u WHERE u.username = :username";
    public static final String USER_BY_PHONE_QUERY = "Select u From User u WHERE u.phone = :phone";
    public static final String USER_BY_EMAIL_QUERY = "Select u From User u WHERE u.email = :email";
    public static final String USER_LOGIN_QUERY = "Select u from User u WHERE u.username = :username and u.password = :password";
    public static final String UPDATE_MAIL_QUERY = "UPDATE User u Set u.email = :email, u.phone= :phone, u.address= :address WHERE u.username = :username";
    public static final String DELETE_USER = "DELETE FROM User u WHERE u.id_user =: id_user";
}
