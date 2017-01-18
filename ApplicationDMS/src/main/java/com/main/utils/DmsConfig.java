/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.utils;

import java.io.File;

/**
 *
 * @author mayel-1
 */
public class DmsConfig {
    
    public static final String USERHOME_DIR = System.getProperty("user.home");
    public static final String DMS_CONFIG_PATH = USERHOME_DIR +File.separator+"DMS CONFIG"+File.pathSeparator;
    public static final String DMS_DB_CONFIG_DIR = USERHOME_DIR +File.separator+"DMS CONFIG"+File.pathSeparator+"DB CONFIG";
    public static final String DB_FILE_NAME = "dbconfig.xml";
    public static final String DMS_DB_CONFIG_PATH = USERHOME_DIR +File.separator+"DMS CONFIG"+File.pathSeparator+"DB CONFIG"+File.separator+DB_FILE_NAME;
    
}
