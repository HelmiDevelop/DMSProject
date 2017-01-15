/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.management.customer.overview;


/**
 *
 * @author mayel-1
 * @param <Object>
 */
@FunctionalInterface
public interface CustomerConsumer<Object> {
    void accept(Object t);
}
