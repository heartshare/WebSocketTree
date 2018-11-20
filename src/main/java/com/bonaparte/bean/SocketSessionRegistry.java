package com.bonaparte.bean;

import org.springframework.util.Assert;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SocketSessionRegistry {

    //this map save every session
    //这个集合存储session
    private final ConcurrentMap<String, String> userSessionIds = new ConcurrentHashMap();
    private final Object lock = new Object();

    public SocketSessionRegistry() {
    }

    /**
     *
     * 获取sessionId
     * @param user
     * @return
     */
    public String getSessionId(String user) {
        return this.userSessionIds.get(user);
    }

    /**
     * 获取所有session
     * @return
     */
    public ConcurrentMap<String, String> getAllSessionIds() {
        return this.userSessionIds;
    }

    /**
     * register session
     * @param user
     * @param sessionId
     */
    public void registerSessionId(String user, String sessionId) {
        Assert.notNull(user, "User must not be null");
        Assert.notNull(sessionId, "Session ID must not be null");
        Object var3 = this.lock;
        synchronized(this.lock) {
            this.userSessionIds.put(user, sessionId);
        }
    }

    public void unregisterSessionId(String userName, String sessionId) {
        Assert.notNull(sessionId, "Session ID must not be null");
        Object var3 = this.lock;
        synchronized(this.lock) {
            this.userSessionIds.remove(sessionId);
        }
    }
}
