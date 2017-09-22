package ubcorbit.org.testapp.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class RandomAccessService extends IntentService {

    private static int instanceCount = 0;
    private static int handleCount = 0;
    private static String TAG = "orbitRandomAccSe";
    private static String RECORD_NAME = "random-access.log";

    public RandomAccessService() {
        super("RandomAccessService (" + Integer.toString(++instanceCount) + ")");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int callId = ++handleCount;

        Log.i(TAG, String.format("onHandleIntent() (%d)", callId));

        int accesses = 1000;
        int array_size = 1000000;
        int delay = 1000;

        int errors = randomAccesses(array_size, accesses, delay);

        Log.i(TAG, String.format("callId = %d, errors = %d / %d", callId, errors, accesses));
        // todo: log to file

    }

    private int randomAccesses(int array_size, int accesses, int delay) {

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
            int next = rng.nextInt();
            if (array[next] != next) {
                errors++;
            }
        }

        return errors;

    }

}
