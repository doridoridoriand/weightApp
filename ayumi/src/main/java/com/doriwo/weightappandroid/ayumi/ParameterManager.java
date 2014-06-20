package com.doriwo.weightappandroid.ayumi;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Dorian on 2014/06/20.
 */
public class ParameterManager {
    public static final String AUTHORITY = "com.doriwo.weightappandroid.ayumi.provider.weight";

    public static final class Parameters implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/place");

        public static final String CONTENT_TYPE ="vnd.android.cursor.dir/vnd.doriwo.weightappandroid.ayumi.provider.weight";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.doriwo.weightappandroid.ayumi.provider.weight";


    }
}
