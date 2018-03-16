package marshmallow.moolah.Activities;

import android.content.Intent;
import android.graphics.ColorFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import marshmallow.moolah.R;

/**
 * Created by George on 3/15/2018.
 */

public class ProgressActivity extends MoolahActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        // Update Banner
        TextView progressBanner = findViewById(R.id.progressBanner);
        String banner = "Getting your account ready " + username + " ...";
        progressBanner.setText(banner);

        // Kick off progress bar
        ProgressHandler progressHandler = new ProgressHandler();
        progressHandler.execute();
    }


    private class ProgressHandler extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            publishProgress(0);
            SystemClock.sleep(2500);

            publishProgress(25);
            SystemClock.sleep(2500);

            publishProgress(65);
            SystemClock.sleep(2500);

            publishProgress(100);
            SystemClock.sleep(2500);

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            ProgressBar progressBar = findViewById(R.id.progressBar);
            TextView statusText = findViewById(R.id.statusText);
            Integer progressPercent = values[0];

            progressBar.setProgress(progressPercent);
            switch (progressPercent) {
                case 0:
                    statusText.setText("Establishing Secure Connection...");
                    break;
                case 25:
                    statusText.setText("Logging In...");
                    break;
                case 65:
                    statusText.setText("Retrieving Data...");
                    break;
                case 100:
                    statusText.setText("Data Retrieval Complete!");
                    //progressBar.getProgressDrawable().setColorFilter(25600);
                    break;
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
