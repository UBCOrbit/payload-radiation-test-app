package ubcorbit.org.testapp.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import ubcorbit.org.testapp.common.Actions;

/**
 * Created by bulat on 22/09/17 for TestApp
 */
public class StartupService extends Service {

    private static final String TAG = "orbitStartupSe";

    @Override
    public void onCreate() {

        super.onCreate();
        Log.i(TAG, "onCreate()");

        TestStatusReceiver receiver = new TestStatusReceiver();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.registerReceiver(receiver, new IntentFilter(Actions.ALLOC_CHECK_DONE));

        Intent i = new Intent(this, AllocateCheckService.class);
        i.putExtra(AllocateCheckService.ITAG_DELAY, 1000);
        i.putExtra(AllocateCheckService.ITAG_ARRAY, 10000);
        startService(i);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand()");
        return Service.START_REDELIVER_INTENT; // todo: is this the best strategy?
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    private class TestStatusReceiver extends BroadcastReceiver {

        private final String TAG = "TestStatusReceiver";

        private TestStatusReceiver() {}

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive()");
            // todo: switch on intent action to restart the appropriate service
        }

    }

}
