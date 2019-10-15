package com.example.aclass.gson;

import com.google.gson.annotations.SerializedName;

public class Forecast {
    public String date;
    @SerializedName("tmp")
    public Temperature temperature;
    @SerializedName("more")
    public More more;
    public class Temperature {
        public String max;
        public String min;

    }

    public class More {

        @SerializedName("text_date")
        public String info;
    }
}
