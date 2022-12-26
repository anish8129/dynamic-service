/*
 * Copyright 2022 Play Games24x7 Pvt. Ltd. All Rights Reserved
 */

package com.dynamic.archiaus;

public class KeyValueDetails {
    private String serviceName;
    private String key;
    private String value;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "KeyValueDetails{" +
                "serviceName='" + serviceName + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
