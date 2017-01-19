/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mayel-1
 */
public class PersistenceMap {
      
    
    static String JDBC_URL_KEY = "javax.persistence.jdbc.url";
    static String JDBC_USER_KEY = "javax.persistence.jdbc.user";
    static String JDBC_PWT_KEY = "javax.persistence.jdbc.password";
    static String JDBC_DRIVER_KEY = "javax.persistence.jdbc.driver";
    
    static final String JDBC_DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    static final String JDBC_DRIVER_SQLITE = "org.sqlite.JDBC";
    
    public static Map<String, String> PersistenceProperties;
    
    public static void CreatePersistencePropertyMap(String url, String dbName, String username, String password, String driver){
        Map<String, String> map = new HashMap<>();
        String urlPrefix = getDbUrlPrefix(driver);
        String dbUrl = urlPrefix+url + File.separator + dbName;
        map.put(JDBC_URL_KEY, dbUrl);
        map.put(JDBC_USER_KEY, username);
        map.put(JDBC_PWT_KEY, password);
        map.put(JDBC_DRIVER_KEY, driver);        
        PersistenceProperties = map;
    }
    private static String getDbUrlPrefix(String driver){
        switch(driver){
            case JDBC_DRIVER_SQLITE:
                return "jdbc:sqlite:";                
            case JDBC_DRIVER_MYSQL:
                return "jdbc:mysql:";
            default:
                return "UNKNOWN:DRIVER:";
        }
    }
}
