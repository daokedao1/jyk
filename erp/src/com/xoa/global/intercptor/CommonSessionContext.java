package com.xoa.global.intercptor;

import com.xoa.util.common.L;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by 韩东堂on 2017/6/27.
 */
public class CommonSessionContext {
    private static CommonSessionContext instance;
    private HashMap mymap;

    private CommonSessionContext() {
        mymap = new HashMap();
    }

    public static CommonSessionContext getInstance() {
        if (instance == null) {
            synchronized (CommonSessionContext.class){
                if(instance == null){
                    instance = new CommonSessionContext();
                }
            }
        }
        return instance;
    }

    public synchronized void AddSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session);
        }
    }

    public synchronized void DelSession(HttpSession session) {
        L.s("run remove session");
        if (session != null) {
            mymap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String session_id) {
        if (session_id == null)
            return null;
        return (HttpSession) mymap.get(session_id);
    }
}
