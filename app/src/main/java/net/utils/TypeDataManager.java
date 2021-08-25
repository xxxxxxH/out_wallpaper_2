package net.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;

import net.basicmodel.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeDataManager {
    private static TypeDataManager instance = null;

    private TypeDataManager() {
    }

    public static TypeDataManager getInstance() {
        if (instance == null) {
            instance = new TypeDataManager();
        }
        return instance;
    }

    public HashMap<String, String> getData(Context context) {
        HashMap<String, String> result = new HashMap<>();
        result.put("car", getMipmap(context, R.mipmap.car_bg));
        result.put("car2", getMipmap(context, R.mipmap.car2_bg));
        result.put("bear", getMipmap(context, R.mipmap.bear_bg));
        result.put("dragon", getMipmap(context, R.mipmap.dragon_bg));
        result.put("eagle", getMipmap(context, R.mipmap.eagle_bg));
        result.put("fruit", getMipmap(context, R.mipmap.fruit_bg));
        result.put("meteor", getMipmap(context, R.mipmap.meteor_bg));
        result.put("snowscene", getMipmap(context, R.mipmap.snowscene_bg));
        result.put("summer", getMipmap(context, R.mipmap.summer_bg));
        result.put("nba", getMipmap(context, R.mipmap.nba_bg));
        result.put("painting", getMipmap(context, R.mipmap.painting_bg));
        result.put("game", getMipmap(context, R.mipmap.game_bg));
        result.put("gun", getMipmap(context, R.mipmap.gun_bg));
        result.put("celestial", getMipmap(context, R.mipmap.celestial_bg));
        result.put("fighter", getMipmap(context, R.mipmap.fighter_bg));
        result.put("landmark", getMipmap(context, R.mipmap.landmark_bg));
        result.put("music", getMipmap(context, R.mipmap.music_bg));
        result.put("news", getMipmap(context, R.mipmap.news_bg));
        result.put("space", getMipmap(context, R.mipmap.space_bg));
        result.put("tank", getMipmap(context, R.mipmap.tank_bg));
        result.put("waterfall", getMipmap(context, R.mipmap.waterfall_bg));
        result.put("animal", getMipmap(context, R.mipmap.animal_bg));
        result.put("castle", getMipmap(context, R.mipmap.castle_bg));
        result.put("dog", getMipmap(context, R.mipmap.dog_bg));
        result.put("bird", getMipmap(context, R.mipmap.bird_bg));
        result.put("fireworks", getMipmap(context, R.mipmap.fireworks_bg));
        result.put("letter", getMipmap(context, R.mipmap.letter_bg));
        result.put("nature", getMipmap(context, R.mipmap.nature_bg));
        result.put("nightview", getMipmap(context, R.mipmap.nightview_bg));
        result.put("parrot", getMipmap(context, R.mipmap.parrot_bg));
        result.put("spark", getMipmap(context, R.mipmap.spark_bg));
        result.put("tree", getMipmap(context, R.mipmap.truck_bg));
        result.put("aquarium", getMipmap(context, R.mipmap.aquarium_bg));
        result.put("brokeglass", getMipmap(context, R.mipmap.brokeglass_bg));
        result.put("cat", getMipmap(context, R.mipmap.cat_bg));
        result.put("flower", getMipmap(context, R.mipmap.flower_bg));
        result.put("natureh", getMipmap(context, R.mipmap.natureh_bg));
        result.put("oceanwave", getMipmap(context, R.mipmap.oceanwave_bg));
        result.put("sdolphin", getMipmap(context, R.mipmap.dolphin_bg));
        result.put("spring", getMipmap(context, R.mipmap.spring_bg));
        result.put("truck", getMipmap(context, R.mipmap.truck_bg));
        return result;
    }

    public ArrayList<String> getKeys(HashMap<String, String> data) {
        ArrayList<String> result = new ArrayList<>(data.keySet());
        return result;
    }

    public ArrayList<String> getValues(HashMap<String, String> data) {
        ArrayList<String> result = new ArrayList<>(data.values());
        return result;
    }

    public String getMipmap(Context context, int id) {
        Resources resources = context.getResources();
        Uri url = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
                + resources.getResourceEntryName(id));
        return url.toString();
    }
}
