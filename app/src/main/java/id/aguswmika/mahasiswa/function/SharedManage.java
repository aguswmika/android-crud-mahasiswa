package id.aguswmika.mahasiswa.function;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedManage {
    private SharedPreferences sharedPref;
    private String SHARE_PREF_NAME = "mahasiswa_pref";

    public SharedManage(Context context){
        sharedPref = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void write(String key, String value){
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public String read(String key){
        return sharedPref.getString(key, null);
    }
}
