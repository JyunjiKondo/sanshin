
package jp.gr.java_conf.jyukon.sanshin.sanshinclient;

import com.google.code.sanshin.sanshinclient.midiplayer.MidiPlayerClient;
import com.google.code.sanshin.sanshinclient.midiplayer.MidiPlayerClientBTImpl;
import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessoryImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class MyModule extends AbstractModule {

    public MyModule() {

    }

    @Override
    protected void configure() {
        bind(OpenAccessory.class).to(OpenAccessoryImpl.class).in(Scopes.SINGLETON);
        bind(MidiPlayerClient.class).to(MidiPlayerClientBTImpl.class).in(Scopes.SINGLETON);
    }
}
