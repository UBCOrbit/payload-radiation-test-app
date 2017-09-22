package ubcorbit.org.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import ubcorbit.org.testapp.services.FileAppenderService;
import ubcorbit.org.testapp.services.RandomAccessService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "orbitMainAc";
    private static String RECORD_NAME = "main-activity.log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // todo start test services

        Intent i = new Intent(this, FileAppenderService.class);
        i.putExtra(FileAppenderService.ITAG_FILENAME, RECORD_NAME);
        i.putExtra(FileAppenderService.ITAG_CONTENT, "hello");
        startService(i);

        Intent i2 = new Intent(this, RandomAccessService.class);
        startService(i2);

    }

}
