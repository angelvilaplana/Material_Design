package orihuel.vilaplana.angel.materialdesign;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

/**
 * Startup activity
 */
public class MainActivity extends AppCompatActivity {

    private Preferences preferences;

    private boolean darkMode;

    private boolean userIsInteracting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set user interaction
        userIsInteracting = false;
        // Set language preferences
        preferences = new Preferences(this);
        preferences.setLanguage();
        setContentView(R.layout.activity_main);
        setUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        switch (item.getItemId()) {
            case R.id.showDiscs:
                handleShowDiscs(view);
                break;
            case R.id.editDisc:
                handleEditDisc(view);
                break;
            case R.id.about:
                handleAbout(view);
                break;
            case R.id.exit:
                handleExit(view);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUI() {
        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        // FloatingActionButton EditDisc
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEditDisc(view);
            }
        });

        // Set spinner language
        setSpinnerLanguage();
        // Set dark mode
        setDarkMode();
    }

    private void setSpinnerLanguage() {
        final Spinner spinner = findViewById(R.id.spinnerLanguage);
        LanguageAdapter adapter = new LanguageAdapter(this);
        spinner.setAdapter(adapter);

        // Select actual language item save in preferences
        selectItemSpinner(spinner, adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                OperatedLanguage language = (OperatedLanguage) spinner.getSelectedItem();
                String languageNameSelect = language.getName();

                // If user interacting save language preferences
                // and reload activity
                if (userIsInteracting) {
                    preferences.setLanguage(languageNameSelect);
                    recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Select actual language item save in preferences
     *
     * @param spinner Spinner where the item will be selected
     * @param adapter To get the number of items
     */
    private void selectItemSpinner(Spinner spinner, LanguageAdapter adapter) {
        int items = adapter.getCount();
        for (int i = 0; i < items; i++) {
            OperatedLanguage language = (OperatedLanguage) spinner.getItemAtPosition(i);
            if (language.getName().equals(preferences.getLanguageName())) {
                spinner.setSelection(i);
            }
        }
    }

    /**
     * When the user interacts the variable
     * "userIsInteracting" will be true
     */
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userIsInteracting = true;
    }

    /**
     * Put the app in dark mode
     */
    private void setDarkMode() {
        final Button btDarkMode = findViewById(R.id.btDarkMode);

        darkMode = preferences.getDarkMode();

        btDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                darkMode = !darkMode;
                preferences.setDarkMode(darkMode);
                setDarkMode(btDarkMode);
            }
        });


        if (!darkMode) {
            int nightModeFlags = getBaseContext().getResources().
                    getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                darkMode = true;
            }
        }

        setDarkMode(btDarkMode);
    }

    /**
     * If darkMode is true the application will be in dark mode
     *
     * @param btDarkMode Button where the text will change
     */
    private void setDarkMode(Button btDarkMode) {
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            btDarkMode.setText(R.string.disable_dark_mode);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            btDarkMode.setText(R.string.enable_dark_mode);
        }
    }

    /**
     * Goes to the activity "ShowDiscsActivity"
     */
    public void handleShowDiscs(View view) {
        Intent intent = new Intent(this, ShowDiscsActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to the activity "DiscEditingActivity"
     */
    public void handleEditDisc(View view) {
        Intent intent = new Intent(this, DiscEditingActivity.class);
        startActivity(intent);
    }

    /**
     * Show a notification about the app information
     */
    public void handleAbout(View view) {
        Snackbar.make(view, "MaterialDesign by Angel Vilaplana Orihuel", Snackbar.LENGTH_LONG)
                .setAction("Action", null).setTextColor(Color.WHITE)
                .setBackgroundTint(getColor(R.color.colorPrimary)).show();
    }

    /**
     * Exits the application and closes it
     */
    public void handleExit(View view) {
        finishAndRemoveTask();
    }

}