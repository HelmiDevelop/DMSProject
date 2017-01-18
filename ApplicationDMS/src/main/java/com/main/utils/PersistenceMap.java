/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mayel-1
 */
public class PersistenceMap {
    
    private final Map<String, String> persistenceMap;
    
    String JDBC_URL_KEY = "javax.persistence.jdbc.url";
    String JDBC_USER_KEY = "javax.persistence.jdbc.user";
    String JDBC_PWT_KEY = "javax.persistence.jdbc.password";
    String JDBC_DRIVER_KEY = "javax.persistence.jdbc.driver";
    
    private String jdbcUrlValue;
    private String jdbcUserValue;
    private String jdbcPwtValue;
    private String jdbcDriverValue;

    public PersistenceMap(String jdbcUrlValue, String jdbcUserValue, String jdbcPwtValue, String jdbcDriverValue) {
        this.persistenceMap = new HashMap<>();
            
        this.persistenceMap.put(JDBC_URL_KEY, jdbcUrlValue);
        this.persistenceMap.put(JDBC_USER_KEY, jdbcUserValue);
        this.persistenceMap.put(JDBC_PWT_KEY, jdbcPwtValue);
        this.persistenceMap.put(JDBC_DRIVER_KEY, jdbcDriverValue);
    }
    
    

    public String getJdbcUrlValue() {
        if(persistenceMap.containsKey(JDBC_URL_KEY)){
            jdbcUrlValue = persistenceMap.get(JDBC_URL_KEY);
        }
        return jdbcUrlValue;
    }

    public void setJdbcUrlValue(String value) {
        this.persistenceMap.put(JDBC_URL_KEY, value);        
    }

    public String getJdbcUserValue() {
        if(persistenceMap.containsKey(JDBC_USER_KEY)){
            this.JDBC_USER_KEY = persistenceMap.get(JDBC_USER_KEY);
        }
        return jdbcUserValue;
    }

    public void setJdbcUserValue(String value) {
        this.persistenceMap.put(JDBC_USER_KEY, value);
    }

    public String getJdbcPwtValue() {
        if(this.persistenceMap.containsKey(JDBC_PWT_KEY)){
            this.jdbcPwtValue = this.persistenceMap.get(JDBC_PWT_KEY);
        }
        return jdbcPwtValue;
    }

    public void setJdbcPwtValue(String value) {
        this.persistenceMap.put(JDBC_PWT_KEY, value);        
    }

    public String getJdbcDriverValue() {
        if(this.persistenceMap.containsKey(JDBC_DRIVER_KEY)){
            this.jdbcDriverValue = this.persistenceMap.get(JDBC_DRIVER_KEY);
        }
        return jdbcDriverValue;
    }

    public void setJdbcDriverValue(String value) {
        this.persistenceMap.put(JDBC_DRIVER_KEY, value);
    }

    public Map<String, String> getPersistenceMap() {
        return persistenceMap;
    }
    
}
