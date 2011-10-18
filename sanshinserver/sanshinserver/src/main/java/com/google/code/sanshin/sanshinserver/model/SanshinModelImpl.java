
package com.google.code.sanshin.sanshinserver.model;

import java.util.HashMap;

public class SanshinModelImpl {
    private final int MaxChannel = 15;
    private HashMap<String, Integer> mClientMap;
    private int mChannel = 0;

    public SanshinModelImpl() {
        mClientMap = new HashMap<String, Integer>();
    }

    public boolean addClient(String clientId) {
        if (mChannel > MaxChannel) {
            return false;
        }
        mClientMap.put(clientId, new Integer(mChannel));
        mChannel++;
        return true;
    }

    public int getChannel(String clientId) {
        Integer channel = mClientMap.get(clientId);
        if (channel == null) {
            return -1;
        }
        return channel.intValue();
    }
}
