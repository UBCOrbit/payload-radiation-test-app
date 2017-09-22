package ubcorbit.org.testapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import ubcorbit.org.testapp.common.Actions;

import java.util.Random;

public class AllocateCheckService extends IntentService {

    private static int instanceCount = 0;
    private static int handleCount = 1;
    private static String TAG = "orbitAllocSe";
    private static String RECORD_NAME = "alloc-check.txt";
    public static String ITAG_ARRAY = "array-size";
    public static String ITAG_DELAY = "delay";
    private static int DEF_ARRAY = 1000000;
    private static int DEF_DELAY = 6000;

    public AllocateCheckService() {
        super("AllocateCheckService (" + Integer.toString(++instanceCount) + ")");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i(TAG, "onHandleIntent()");
        int callId = handleCount++;

        Log.i(TAG, String.format("onHandleIntent() (%d)", callId));
        int arraySize = (intent.getIntExtra(ITAG_ARRAY, DEF_ARRAY));
        int delay = (intent.getIntExtra(ITAG_DELAY, DEF_DELAY));

        int errors = allocateAndCheckRam(arraySize, delay);
        Log.i(TAG, String.format("callId = %d, errors = %d", callId, errors));

        String log = String.format("(%d) : errors = %d, delay = %d", callId, errors, delay);
        Intent recordIntent = new Intent(this, FileAppenderService.class);
        recordIntent.putExtra(FileAppenderService.ITAG_CONTENT, log);
        recordIntent.putExtra(FileAppenderService.ITAG_FILENAME, RECORD_NAME);
        startService(recordIntent);

        Intent doneIntent = new Intent(Actions.RANDOM_ACCESS_DONE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(doneIntent);

    }

    /**
     * @param size: bytes to write
     * @param delay: milliseconds to switch between writing and checking
     * @return # errors
     */
    private int allocateAndCheckRam(int size, int delay) {

        Log.i(TAG, "allocateAndCheckRam()");

        byte[] array = new byte[size];
        for (int i = 0; i < size; i++) {
            array[i] = 0;
        }

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int errors = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] != 0) {
                errors++;
            }
        }

        for (int i = 0; i < size; i++) {
            array[i] = 0b1111111;
        }

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < size; i++) {
            if (array[i] != 0b1111111) {
                errors++;
            }
        }

        return errors;

    }

}
