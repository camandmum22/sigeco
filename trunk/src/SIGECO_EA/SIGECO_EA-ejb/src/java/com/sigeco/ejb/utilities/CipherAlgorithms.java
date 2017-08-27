/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.utilities;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 <p>
 @author Christian
 */
public class CipherAlgorithms
{
    private static byte[] KEY = hexToBytes( "2b7e151628aed2a6abf7158809cf4f3c" );

    private static String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    private static byte[] hexToBytes( String str )
    {
        if( str == null )
        {
            return null;
        }
        else if( str.length() < 2 )
        {
            return null;
        }
        else
        {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for( int i = 0; i < len; i++ )
            {
                buffer[i] = (byte)Integer.parseInt(
                        str.substring( i * 2, i * 2 + 2 ), 16 );
            }
            return buffer;
        }

    }

    private static String bytesToHex( byte[] data )
    {
        if( data == null )
        {
            return null;
        }
        else
        {
            int len = data.length;
            String str = "";
            for( int i = 0; i < len; i++ )
            {
                if( ( data[i] & 0xFF ) < 16 )
                {
                    str = str + "0"
                          + java.lang.Integer.toHexString( data[i] & 0xFF );
                }
                else
                {
                    str = str + java.lang.Integer.toHexString( data[i] & 0xFF );
                }
            }
            return str.toUpperCase();
        }
    }

    private static String asciiToHex( String asciiValue )
    {
        char[] chars = asciiValue.toCharArray();
        StringBuilder hex = new StringBuilder();
        for( int i = 0; i < chars.length; i++ )
        {
            hex.append( Integer.toHexString( (int)chars[i] ) );
        }
        return hex.toString();
    }

    private static String hexToASCII( String hexValue )
    {
        StringBuilder output = new StringBuilder( "" );
        for( int i = 0; i < hexValue.length(); i += 2 )
        {
            String str = hexValue.substring( i, i + 2 );
            output.append( (char)Integer.parseInt( str, 16 ) );
        }
        return output.toString();
    }

    public static String encryptAES( String password ) throws Exception
    {
        SecretKeySpec ks = new SecretKeySpec( KEY, "AES" );
        Cipher cf = Cipher.getInstance( TRANSFORMATION );
        cf.init( Cipher.ENCRYPT_MODE, ks );

        return bytesToHex( cf.doFinal( hexToBytes( asciiToHex( password ) ) ) );
    }

    public static String decryptAES( String datos ) throws Exception
    {
        SecretKeySpec ks = new SecretKeySpec( KEY, "AES" );
        Cipher cf = Cipher.getInstance( TRANSFORMATION );
        cf.init( Cipher.DECRYPT_MODE, ks );
        
        return hexToASCII( bytesToHex( cf.doFinal( hexToBytes( datos ) ) ) );
    }
}
