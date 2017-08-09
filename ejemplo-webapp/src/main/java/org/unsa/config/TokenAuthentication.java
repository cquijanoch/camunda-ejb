/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unsa.config;

import java.util.logging.Logger;


public class TokenAuthentication {
    private static Logger logger = Logger.getLogger(TokenAuthentication.class.getName());
    
    public boolean isValidAuthentication(String tokenRequest){
        return TokenHandler.getInstance().validarToken(tokenRequest);
    }
}
