/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author misanchez
 */
public class UtilWhere {

    public String getWhere() {
        Field[] varNames = getClass().getDeclaredFields();
        Boolean primerField = true;
        String field, operador, where = "";
        for (Field varName : varNames) {
            try {
                field = "";
                if (varName.getModifiers() == 1 && (varName.get(this) != null && !varName.get(this).toString().isEmpty())) {
                    if (primerField) {
                        where = " WHERE ";
                    }

                    operador = varName.getType().equals(String.class) ? " like " : " = ";

                    String[] names = varName.getName().split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");

                    for (String name : names) {
                        if (field.isEmpty()) {
                            field = name;
                        } else {
                            field += "_" + name;
                        }
                    }
                    if (!primerField) {
                        where += " AND ";
                    }
                    where += field + operador + (varName.getType().equals(String.class) ? "'%" + varName.get(this) + "%'" : varName.get(this));

                    primerField = false;
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(UtilWhere.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return where;
    }

    public String getWhere(String where) {
        Field[] varNames = getClass().getDeclaredFields();
        String field, operador;
        for (Field varName : varNames) {
            try {
                field = "";
                if (varName.getModifiers() == 1 && (varName.get(this) != null && !varName.get(this).toString().isEmpty())) {
                    operador = varName.getType().equals(String.class) ? " like " : " = ";

                    String[] names = varName.getName().split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");

                    for (String name : names) {
                        if (field.isEmpty()) {
                            field = name;
                        } else {
                            field += "_" + name;
                        }
                    }
                    where += " AND ";

                    where += field + operador + (varName.getType().equals(String.class) ? "'%" + varName.get(this) + "%'" : varName.get(this));

                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(UtilWhere.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return where;
    }
}
