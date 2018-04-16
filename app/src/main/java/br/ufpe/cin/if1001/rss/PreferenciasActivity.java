package br.ufpe.cin.if1001.rss;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.ufpe.cin.if1001.rss.Utilities.Constants;

public class PreferenciasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
    }

    public void onSavePreferencesClick(View view){
        EditText rssFeedTextField = (EditText) findViewById(R.id.rssFeedText);
        String newRssFeedPreference = rssFeedTextField.getText().toString();
        if(!newRssFeedPreference.isEmpty()){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Constants.RSS_FEED, newRssFeedPreference);
            editor.apply();
            Toast.makeText(getApplicationContext(),"Preferências salvas com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    public static class RssPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
    }
}