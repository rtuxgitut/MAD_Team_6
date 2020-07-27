package sg.edu.np.madteam6.assignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PreConfig {
    //Key to save and store purchase records added by user
    private static final String DATABASE_KEY = "MY_DATA";

    //Writes and Records bbt name and price inputs made by user to Shared Preference
    public static void writeListInPref(Context context, List<ViewPurchaseModel> list) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(DATABASE_KEY, jsonString);
        editor.apply();
    }

    //Retrieves and loads user inputs from Shared Preference to the View Purchase (RecyclerView) screen.
    public static List<ViewPurchaseModel> readListFromPref(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(DATABASE_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ViewPurchaseModel>>() {}.getType();
        List<ViewPurchaseModel> list = gson.fromJson(jsonString, type);
        return list;
    }
}
