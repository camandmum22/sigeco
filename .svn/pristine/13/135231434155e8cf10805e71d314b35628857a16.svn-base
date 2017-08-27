/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.utilities;

/**
 *
 * @author csacanam
 */
public class InputChecks
{

    public static boolean isEmptyOrNull( String cadena )
    {
        if( cadena == null )
        {
            return true;
        }

        return cadena.isEmpty();
    }

    public static boolean isTrimEmptyOrNull( String cadena )
    {
        if( cadena == null )
        {
            return true;
        }

        return cadena.trim().isEmpty();
    }

    public static boolean isAnyEmptyorNull( String... cadenas )
    {
        for( String cadena : cadenas )
        {
            if( isEmptyOrNull( cadena ) )
            {
                return true;
            }
        }

        return false;
    }

    public static boolean isAnyTrimEmptyorNull( String... cadenas )
    {
        for( String cadena : cadenas )
        {
            if( isTrimEmptyOrNull( cadena ) )
            {
                return true;
            }
        }

        return false;
    }
}
