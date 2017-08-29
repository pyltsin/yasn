package com.getjavajob.project.pyltsin.project.common.to;

/**
 * Created by Pyltsin on 02.03.2017. Algo8
 */
public class ItemFind {
    private String name;
    private String login;
    private String url;
    private String label;
    private String value;

    public ItemFind(String name, String login) {
        this.name = name;
        this.login = login;
        this.url = getUrlFromName();
        this.label = name;
        this.value = url;
    }

    public String getUrl() {
        return url;
    }

    private String getUrlFromName() {
        return "account?login=" + login;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

