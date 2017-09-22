package ubcorbit.org.testapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import ubcorbit.org.testapp.services.MemoryTestService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "orbitMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void appendStringToFile(String content, String fileName) {

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
                Log.e(TAG, "could not write to file output stream");
            }

        } else {
            Log.e(TAG, "external storage is not writeable");
        }

    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    private File getStorageDir() {

        File dir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "TestApp");
        if (!dir.mkdirs()) Log.e(TAG, "storage directory not created");
        return dir;

    }

}
