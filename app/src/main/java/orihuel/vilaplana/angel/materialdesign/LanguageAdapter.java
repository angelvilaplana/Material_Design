package orihuel.vilaplana.angel.materialdesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Adapter for language spinner
 */
public class LanguageAdapter extends ArrayAdapter<OperatedLanguage> {

    private LayoutInflater layoutInflater;

    public LanguageAdapter(Context context) {
        super(context, 0, OperatedLanguage.values());
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Get language view
     *
     * @param position Position where the language view is
     * @param convertView View where we will convert the information
     * @param parent Parent view (Spinner)
     *
     * @return The view with the language
     */
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        // If is null obtain the language template
        // if not get the converted view
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_language, parent, false);
        } else {
            view = convertView;
        }

        // Put the language in the view
        OperatedLanguage language = getItem(position);
        if (language != null) {
            setItemForLanguage(view, language);
        }

        return view;
    }

    /**
     * When the spinner is displayed
     *
     * @param position Position view
     * @param convertView Convert View
     * @param parent Spinner
     * @return
     */
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Put the item language dropdown template
        View view = layoutInflater.inflate(R.layout.item_language_dropdown, parent, false);

        // Get language position
        OperatedLanguage language = getItem(position);
        if (language != null) {
            setItemForLanguage(view, language);
        }

        return view;
    }

    /**
     * Set values in the item view
     *
     * @param view The view
     * @param language The language
     */
    private void setItemForLanguage(View view, OperatedLanguage language) {
        TextView tvLanguage = view.findViewById(R.id.tvLanguage);
        ImageView ivLanguage = view.findViewById(R.id.ivLanguage);

        tvLanguage.setText(language.getName());
        ivLanguage.setBackgroundResource(language.getIcon());
    }

}
