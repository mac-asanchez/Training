package com.example.user.analyticsapp;

import org.json.JSONException;
import org.json.JSONObject;

public class Event {
    String name;
    JSONObject props;

    public Event(String name, JSONObject props) {
        this.name = name;
        this.props = props;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject getProps() {
        return props;
    }

    public void setProps(JSONObject props) {
        this.props = props;
    }

    public static class PropertyBuilder {
        JSONObject jsonObject;

        public PropertyBuilder() {
            jsonObject = new JSONObject();
        }

        public PropertyBuilder addProp(String propName, Object propValue) {
            try {
                jsonObject.put(propName, propValue);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return this;
        }

        public JSONObject build() {
            return jsonObject;
        }
    }
}
