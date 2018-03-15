package marshmallow.moolah.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by George on 3/14/2018.
 */

public class MoolahActivity extends AppCompatActivity {
    protected static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void setUsername(String username) {
        MoolahActivity.username = username;
    }
}
