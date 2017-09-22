package ubcorbit.org.testapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by bulat on 21/09/17 for TestApp
 */
public class FileAppenderService extends IntentService{

    private static int instanceCount = 0;
    private static String TAG = "orbitFileAppenderSe";
    public static String ITAG_CONTENT = "content";
    public static String ITAG_FILENAME = "filename";

    public FileAppenderService() {
        super("FileAppenderService (" + Integer.toString(++instanceCount) + ")");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String content = intent.getStringExtra(ITAG_CONTENT);
        String filename = intent.getStringExtra(ITAG_FILENAME);
        if(content != null && filename != null) {
            appendStringToFile(content, filename);
        }

    }

    private void appendStringToFile(String content, String fileName) {

        Log.i(TAG, "appendStringToFile()");

        if(isExternalStorageWritable()) {

            try {

                File dir = getStorageDir();
                File file = new File(dir, fileName);
                try (FileOutputStream stream = new FileOutputStream(file, true)) {
                    stream.write(("\n" + content).getBytes());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                Log.e(TAG, "can't write to output stream");
            }

        } else {
            Log.e(TAG, "external storage not writeable");
        }

    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    private File getStorageDir() {

        return new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "TestApp");

    }

}
