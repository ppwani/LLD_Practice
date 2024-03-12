import java.util.HashMap;

public class UserMgr {
    private HashMap<Integer, User> users;
    private HashMap<String, User> aadharToUser;
    private HashMap<Integer, User> receptionists;

    private UserMgr() {
        users = new HashMap<>();
        aadharToUser = new HashMap<>();
        receptionists = new HashMap<>();
    }
    private static UserMgr instance;
    public static UserMgr getInstance(){
        if(instance == null){
            synchronized (HotelManagementSystem.class) {
                if(instance == null)instance = new UserMgr();
            }
        }
        return instance;
    }

    public Integer verifyOrAddUser(String aadharNo){
        if(aadharToUser.get(aadharNo)==null){
            // create new user with this aadhar
            // put this user in DB, get the ID in DB
            User customer = new Customer();
            customer.setId(189);
            users.put(189, customer);
            aadharToUser.put(aadharNo, customer);
        }
        return aadharToUser.get(aadharNo).getId();
    }
    public User getUser(Integer userId){
        return users.get(userId);
    }
    public void addReceptionist(User user){
        receptionists.put(user.getId(), user);
    }
    public User getReceptionist(Integer receptionistId){
        return receptionists.get(receptionistId);
    }
}
