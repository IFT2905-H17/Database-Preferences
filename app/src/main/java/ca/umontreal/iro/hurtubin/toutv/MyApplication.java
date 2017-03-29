package ca.umontreal.iro.hurtubin.toutv;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TouTV.setContext(getApplicationContext());
    }
}
