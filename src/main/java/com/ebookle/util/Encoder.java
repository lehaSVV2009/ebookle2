package com.ebookle.util;

import com.sun.jndi.toolkit.url.UrlUtil;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 01.09.13
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
public class Encoder {

    public static String encode (String text) {

        if (isRussian(text)) {
            text = convertFromRus(text);
        }
        return text;
    }

    private static String convertFromRus (String rus) {

        StringBuilder crypt = new StringBuilder();
        int stringLength = rus.length();
        for (int i = 0; i < stringLength - 1; ++i) {
            crypt.append(((int)rus.charAt(i)) + "-");
        }
        crypt.append((int)rus.charAt(stringLength - 1));
        return crypt.toString();
    }

    private static boolean isRussianSymbol (char symbol) {
        int symbolAscii = (int) symbol;
        if ((symbolAscii > 1039 && symbolAscii < 1103)) {
            return true;
        }
        return false;
    }

    private static boolean isRussian (String text) {

        for (int i = 0; i < text.length(); ++i) {
            if (isRussianSymbol(text.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRussianCrypt (String text) {

        String []symbols = text.split("-");
        if (symbols.length == 0) {
            return false;
        }
        for (String symbol : symbols) {
            try {
                Integer.parseInt(symbol);
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
        return true;
    }

    public static String decode (String text) {

        if (isRussianCrypt(text)) {
            text = convertToRus(text);
        }
        return text;
    }

    private static String convertToRus (String crypt) {

        StringBuilder rus = new StringBuilder();
        String []symbols = crypt.split("-");
        for (String symbol : symbols) {
            try {
                rus.append((char)Integer.parseInt(symbol));
            } catch (NumberFormatException nfe) {
                return crypt;
            }
        }
        return rus.toString();
    }


}
