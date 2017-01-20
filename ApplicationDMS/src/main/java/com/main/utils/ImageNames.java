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
public enum ImageNames {
    
    ADD("ic_add_circle_outline.png"),
    ADD_2X("ic_add_circle_outline_2X.png"),
    ADD_3X("ic_add_circle_outline_3x.png"),
    
    EDIT_PEN("ic_edit.png"),
    EDIT_PEN_2X("ic_edit_2X.png"),
    EDIT_PEN_3X("ic_edit_3x.png"),
    
    CANCEL("ic_cancel.png"),
    CANCEL_2X("ic_cancel_2X.png"),
    CANCEL_3X("ic_cancel_3x.png"),
    
    CHECK("ic_check.png"),
    CHECK_2X("ic_check_2X.png"),
    CHECK_3X("ic_check_3x.png"),
    
    CLEAR("ic_clear.png"),
    CLEAR_2X("ic_clear_2X.png"),
    CLEAR_3X("ic_clear_3x.png"),
    
    HELP("ic_help_outline.png"),
    HELP_2X("ic_help_outline_2X.png"),
    HELP_3X("ic_help_outline_3x.png"),
    
    INFO("ic_info_outline.png"),
    INFO_2X("ic_info_outline_2X.png"),
    INFO_3X("ic_info_outline_3x.png"),
    
    SETTING("ic_settings.png"),
    SETTING_2X("ic_settings_2X.png"),
    SETTING_3X("ic_settings_3x.png"),
    
    LAUNCH("ic_launch.png"),
    LAUNCH_2X("ic_launch_2X.png"),
    LAUNCH_3X("ic_launch_3x.png"),
    
    CHEVRONLEFT("ic_chevron_left.png"),
    CHEVRONLEFT_2X("ic_chevron_left_2X.png"),
    CHEVRONLEFT_3X("ic_chevron_left_3x.png"),
    
    CHEVRONRIGHT("ic_chevron_right.png"),
    CHEVRONRIGHT_2X("ic_chevron_right_2X.png"),
    CHEVRONRIGHT_3X("ic_chevron_right_3x.png"),
    
    CONTENT("ic_content_paste.png"),
    CONTENT_2X("ic_content_paste_2X.png"),
    CONTENT_3X("ic_content_paste_3x.png");
    
    
    
    
    private final String imageName;
    private String bundlePath;

    private ImageNames(String name ) {
        bundlePath = getClass().getResource("/images").toExternalForm();
        this.imageName = name;
    }
    public String Name(){
        return bundlePath +File.separator+imageName;
    }
    
    
    
    
}
