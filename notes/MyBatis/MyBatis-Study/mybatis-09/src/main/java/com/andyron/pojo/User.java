package com.andyron.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Andy Ron
 */
@Data
public class User implements Serializable {

    private int id;
    private String name;
    private String pwd;
}
