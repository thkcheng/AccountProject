package thx.account.book;

import android.app.Application;
import android.content.Context;

public class ThkApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        BaseApp.getInstance().attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BaseApp.getInstance().onCreate(this);
    }

    /**
     * This method is for use in emulated process environments.  It will
     * never be called on a production Android device, where processes are
     * removed by simply killing them; no user code (including this callback)
     * is executed when doing so.
     * <p>
     * 该方法不会被回调
     * </p>
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
