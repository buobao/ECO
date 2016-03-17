package com.joint.turman.app.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by dqf on 2016/3/17.
 */
public class ParamsMap implements Serializable {
    private static final long serialVersionUID = -645821020648740992L;

    public ParamsMap(Map<String, Object> map) {
        this.map = map;
    }

    private Map<String, Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
