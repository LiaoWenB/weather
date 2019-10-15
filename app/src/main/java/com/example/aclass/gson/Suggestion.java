package com.example.aclass.gson;

import com.google.gson.annotations.SerializedName;

public class Suggestion {

    @SerializedName("comfortable")
    public Comfort comfort;

    public Sport sport;

    public class Comfort {
        @SerializedName("txt")
        public String info;
    }

    public class Sport {
        @SerializedName("txt")
        public String info;
    }
}
