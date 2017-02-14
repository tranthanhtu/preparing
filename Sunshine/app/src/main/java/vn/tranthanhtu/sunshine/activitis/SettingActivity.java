package vn.tranthanhtu.sunshine.activitis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.models.NextDayModel;
import vn.tranthanhtu.sunshine.services.LoadDataCurrentDayWeather;
import vn.tranthanhtu.sunshine.services.LoadDataNextDayWeather;

public class SettingActivity extends PreferenceActivity  implements
        SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NextDayModel.list.clear();
        startService(new Intent(this, LoadDataCurrentDayWeather.class));
        startService(new Intent(this, LoadDataNextDayWeather.class));
        startActivity(new Intent(this, WaitActivity.class));

    }

    private void setPreferenceSummary(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }
    }


    //    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//       if (key.equals("location")){
//
//       }else if (key.equals("units")){
//
//       }
//    }
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference1 = findPreference(key);
        if (sharedPreferences instanceof EditTextPreference){
            EditTextPreference editTextPreference = (EditTextPreference) preference1;
            String edtLocation = editTextPreference.getText();
            preference1.setSummary(edtLocation);
        }
        Preference preference = findPreference(key);
        if (null != preference) {
            if (!(preference instanceof CheckBoxPreference)) {
                setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
            }
        }



    }

    @Override
    public void onStop() {
        super.onStop();
        // unregister the preference change listener
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // register the preference change listener
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }



}
