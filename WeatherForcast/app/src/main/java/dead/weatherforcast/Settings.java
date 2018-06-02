package dead.weatherforcast;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content,new SettingsFragment()).commit();
    }


    public static class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_prefernces);
            Preference location =findPreference(getString(R.string.location_key));//like findViewById
            location.setOnPreferenceChangeListener(this);   //like button
            onPreferenceChange(location,
                    PreferenceManager.getDefaultSharedPreferences(location.getContext()).
                            getString(location.getKey(),"London")
            );
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            String value=o.toString();
            preference.setSummary(value);
            return true;
        }
    }
}
