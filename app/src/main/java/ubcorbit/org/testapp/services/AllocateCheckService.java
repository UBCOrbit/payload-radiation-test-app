package ubcorbit.org.testapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class AllocateCheckService extends IntentService {

    private static int instanceCount = 0;
    private static String TAG = "orbitAllocSe";

    public AllocateCheckService() {
        super("AllocateCheckService (" + Integer.toString(++instanceCount) + ")");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i(TAG, "onHandleIntent()");


    }

    /**
     * @param size: bytes to write
     * @param time: milliseconds to switch between writing and checking
     * @return # errors
     */
    private int allocateAndCheckRam(int size, int time) {

        byte[] array = new byte[size];
        for (int i = 0; i < size; i++) {
            array[i] = 0;
        }

        try {
            Thread.sleep(time);
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
            Thread.sleep(time);
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
