package orihuel.vilaplana.angel.materialdesign;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Disc editing activity
 */
public class DiscEditingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // We load the language settings
        Preferences preferences = new Preferences(this);
        preferences.setLanguage();
        setContentView(R.layout.activity_disc_editing);
    }

}