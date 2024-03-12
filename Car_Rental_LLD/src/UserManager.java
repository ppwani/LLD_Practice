import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private Map<String, User> users;

    public UserManager(){
        users = new HashMap<>();
    }

    public User getUser(String userId){
        return users.get(userId);
    }
}
