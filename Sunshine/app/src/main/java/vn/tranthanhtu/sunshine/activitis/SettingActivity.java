package vn.tranthanhtu.sunshine.activitis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.models.NextDayModel;

@SuppressWarnings("ALL")
public class SettingActivity extends PreferenceActivity  implements
        SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount();
        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);
            if (!(p instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }else if (!(p instanceof ListPreference)){
                Boolean value = sharedPreferences.getBoolean(p.getKey(), true);
                setPreferenceSummary(p, value);
            }else if (!(p instanceof EditTextPreference)){
                String valure = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, valure);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NextDayModel.list.clear();
        startActivity(new Intent(this, WaitActivity.class));

    }

    private void setPreferenceSummary(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            preference.setSummary(stringValue);
        }
    }

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
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }
}
