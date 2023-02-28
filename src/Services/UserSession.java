/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Amine
 */

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
