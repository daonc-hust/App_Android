package com.example.congdao.merrychristmas;

/**
 * Created by Cong Dao on 12/15/2017.
 */

public class Music {
    private String name;
    private String singer;

    public Music(String name, String singer) {
        this.name = name;
        this.singer = singer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
