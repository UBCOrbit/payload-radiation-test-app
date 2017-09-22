package ubcorbit.org.testapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by bulat on 22/09/17 for TestApp
 */
public class StartupService extends Service {

    private static final String TAG = "orbitStartupSe";

    @Override
    public void onCreate() {

        super.onCreate();
        Log.i(TAG, "onCreate()");

        Intent recordIntent = new Intent(getBaseContext(), FileAppenderService.class);
        recordIntent.putExtra(FileAppenderService.ITAG_CONTENT, "sdfsrh");
        recordIntent.putExtra(FileAppenderService.ITAG_FILENAME, "log.txt");
        startService(recordIntent);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "onStartCommand()");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
