package com.rodri.practica2_SCB;

import java.io.*;

/**
 * Created by Rodrigo Alvarez Echarri
 * Date: 02/10/12
 * Time: 12:02 PM
 * 
 */
public class Filtro implements FilenameFilter{
    String extension;
    Filtro(String extension){
        this.extension=extension;
    }
    public boolean accept(File dir, String name){
        return name.endsWith(extension);
    }
}
