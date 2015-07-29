/**********************************************************************************
 * Copyright 2015 Tom Praschan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ********************************************************************************/

package de.tap.easy_xkcd;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;

import com.tap.xkcd_reader.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;


public class OfflineComic {
    private String[] mComicData;
    private int mComicNumber;
    private Bitmap mBitmap;

    public OfflineComic(Integer number, Context context){
        mComicNumber = number;
        SharedPreferences preferences = ((MainActivity) context).getPreferences(Activity.MODE_PRIVATE);
        mComicData = new String[2];
        mComicData[0] = preferences.getString("title"+String.valueOf(number),"");
        mComicData[1] = preferences.getString("alt"+String.valueOf(number),"");
        try {
            FileInputStream fis = context.openFileInput(String.valueOf(number));
            mBitmap = BitmapFactory.decodeStream(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getComicData() {
        return mComicData;
    }
    public Bitmap getBitmap() {
        return mBitmap;
    }
    public int getComicNumber() { return mComicNumber;}

}

