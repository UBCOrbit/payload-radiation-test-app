package ubcorbit.org.testapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button testButton;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testButton = (Button) findViewById(R.id.test_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendStringToInteger("i'm a strong radiation boy", "dontpanic.txt");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        appendStringToInteger(Integer.toString(counter++), "dummy.txt");
    }

    private void appendStringToInteger(String content, String fileName) {

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
                Log.e(TAG, "something went wrong (check permission)");
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

        File dir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "TestApp");
        if (!dir.mkdirs()) Log.e(TAG, "Directory not created");
        return dir;

    }

}
