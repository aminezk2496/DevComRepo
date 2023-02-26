package Services;

import java.util.HashMap;

public final class UserSession {
    private HashMap<String,Object> store = new HashMap();
    public static final UserSession INSTANCE = new UserSession();
    public void put(String key, Object value)
    {
        this.store.put(key,value);
    }
    public Object get(String key)
    {
        return this.store.get(key);
    }
    public void remove(String key, Object value)
    {
        this.store.remove(key,value);
    }
    }
