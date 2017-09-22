package ubcorbit.org.testapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import ubcorbit.org.testapp.services.StartupService;

/**
 * Created by bulat on 22/09/17 for TestApp
 */
public class BootupReceiver extends BroadcastReceiver {

    private static final String TAG = "orbitBootupRe";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "onReceive()");

        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            Log.i(TAG, "attempting to start StartupService");
            Intent i = new Intent(context, StartupService.class);
            context.startService(i);
        } else {
            Log.d(TAG, "not the right intent");
        }

    }
}
