package thx.account.book;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;


/**
 * @author thkcheng
 */
public class BaseApp {

    private Context context;

    private ActivityCallback lifeCallback;

    private BaseApp() {
    }

    public static BaseApp getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private final static BaseApp INSTANCE = new BaseApp();
    }

    public static Context getContext() {
        return getInstance().context;
    }

    public void attachBaseContext(Context base) {
        context = base;
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        lifeCallback.finishAll();
    }

    public void onCreate(Application application) {

        lifeCallback = new ActivityCallback();
        application.registerActivityLifecycleCallbacks(lifeCallback);
    }

}
