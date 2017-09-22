package ubcorbit.org.testapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import ubcorbit.org.testapp.common.Actions;

public class IncrementService extends IntentService {

    private static int instanceCount = 0;
    private static int handleCount = 1;
    private static String TAG = "orbitIncrementSe";
    private static String RECORD_NAME = "increment-check.txt";
    public static String ITAG_COUNT = "count";
    private static int DEF_COUNT = Integer.MAX_VALUE;

    public IncrementService() {
        super("IncrementService (" + Integer.toString(++instanceCount) + ")");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int callId = handleCount++;
        Log.i(TAG, String.format("onHandleIntent() (%d)", callId));

        int count = intent.getIntExtra(ITAG_COUNT, DEF_COUNT);
        int delta = checkIncrementDifference(count);

        Log.i(TAG, String.format("callId = %d, delta = %d", callId, delta));
        String log = String.format("(%d) : delta = %d", callId, delta);
        Intent recordIntent = new Intent(this, FileAppenderService.class);
        recordIntent.putExtra(FileAppenderService.ITAG_CONTENT, log);
        recordIntent.putExtra(FileAppenderService.ITAG_FILENAME, RECORD_NAME);
        startService(recordIntent);

        Intent doneIntent = new Intent(Actions.INCREMENT_CHECK_DONE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(doneIntent);

    }

    /**
     * @param n : value to count to
     * @return : difference between actual and expected value
     */
    private int checkIncrementDifference(int n) {

        int total = 0;
        for (long i = 0; i < n; i++) {
            total += n;
        }

        return n - total;
    }

}
