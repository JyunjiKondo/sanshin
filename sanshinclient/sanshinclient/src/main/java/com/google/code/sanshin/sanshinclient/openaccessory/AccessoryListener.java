
package com.google.code.sanshin.sanshinclient.openaccessory;

public interface AccessoryListener {
    public void onAccessoryMessage(byte[] data, int length);
}
