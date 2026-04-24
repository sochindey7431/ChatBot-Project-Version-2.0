public class Admin extends User {

    public Admin(String email, String mobile, String password) {
        super(email, mobile, password);
    }

    public void showAdminPanel() {
        System.out.println("Welcome Admin Panel");
    }
}