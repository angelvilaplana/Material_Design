package orihuel.vilaplana.angel.materialdesign;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Class that gets and saves the application data
 */
public class Preferences {

    private SharedPreferences preferences;

    private Context context;

    public Preferences(Context context) {
        this.preferences = context.getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        this.context = context;
    }

    /**
     * Save the application language
     *
     * @param languageName Name of the language to save
     */
    public void setLanguage(String languageName) {
        SharedPreferences.Editor editor = preferences.edit();
        Locale locale;

        // Check the language
        if (languageName.equals(OperatedLanguage.ENGLISH.getName())) {
            locale = new Locale("en");
        } else if (languageName.equals(OperatedLanguage.SPANISH.getName())) {
            locale = new Locale("es");
        } else if (languageName.equals(OperatedLanguage.CATALAN.getName())) {
            locale = new Locale("ca");
        } else if (languageName.equals(OperatedLanguage.FRENCH.getName())) {
            locale = new Locale("fr");
        } else {
            locale = null;
        }

        // If is a different language put the default language
        if (locale == null) {
            locale = new Locale("en");
        }

        // Save the data
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration configuration = new Configuration(res.getConfiguration());
        configuration.setLocale(locale);
        res.updateConfiguration(configuration, res.getDisplayMetrics());
        editor.putString("languageName", languageName);
        editor.apply();
    }

    /**
     * Set current language
     */
    public void setLanguage() {
        String languageName = getLanguageName();
        setLanguage(languageName);
    }

    /**
     * Get saved language name
     */
    public String getLanguageName() {
        return preferences.getString("languageName", OperatedLanguage.ENGLISH.getName());
    }

    /**
     * Put in dark mode the application
     *
     * @param mode If the application will be in dark mode or not
     */
    public void setDarkMode(boolean mode) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("darkMode", mode);
        editor.apply();
    }

    /**
     * Get if the application is in dark mode or not
     */
    public boolean getDarkMode() {
        return preferences.getBoolean("darkMode", false);
    }

}
