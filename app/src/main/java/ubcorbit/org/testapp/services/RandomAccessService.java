package ubcorbit.org.testapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import ubcorbit.org.testapp.common.Actions;

import java.util.Random;

public class RandomAccessService extends IntentService {

    private static int instanceCount = 0;
    private static int handleCount = 0;
    private static String TAG = "orbitRandomAccSe";
    private static String RECORD_NAME = "random-access.txt";
    public static String ITAG_ACCESSES = "accesses";
    public static String ITAG_ARRAY = "array-size";
    public static String ITAG_DELAY = "delay";

    private static final int DEF_ACCESSES = 10000;
    private static final int DEF_ARRAY = 1000000;
    private static final int DEF_DELAY = 6000;

    public RandomAccessService() {
        super("RandomAccessService (" + Integer.toString(++instanceCount) + ")");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int callId = ++handleCount;

        Log.i(TAG, String.format("onHandleIntent() (%d)", callId));

        int accesses = (intent.getIntExtra(ITAG_ACCESSES, DEF_ACCESSES));
        int array_size = (intent.getIntExtra(ITAG_ARRAY, DEF_ARRAY));
        int delay = (intent.getIntExtra(ITAG_DELAY, DEF_DELAY));

        int errors = randomAccesses(array_size, accesses, delay);

        Log.i(TAG, String.format("callId = %d, errors = %d / %d", callId, errors, accesses));

        String log = String.format("(%d) : errors = %d, accesses = %d, arr_size = %d, delay = %d", callId, errors, accesses, array_size, delay);
        Intent recordIntent = new Intent(this, FileAppenderService.class);
        recordIntent.putExtra(FileAppenderService.ITAG_CONTENT, log);
        recordIntent.putExtra(FileAppenderService.ITAG_FILENAME, RECORD_NAME);
        startService(recordIntent);

        Intent doneIntent = new Intent(Actions.RANDOM_ACCESS_DONE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(doneIntent);

    }

    private int randomAccesses(int array_size, int accesses, int delay) {

        Log.i(TAG, "randomAccess()");

        Random rng = new Random();

        int[] array = new int[array_size];
        for (int i = 0; i < array_size; i++) {
            array[i] = i;
        }

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int errors = 0;
        for (int i = 0; i < accesses; i++) {
            int next = rng.nextInt(array_size);
            if (array[next] != next) {
                errors++;
            }
        }

        return errors;

    }

}
