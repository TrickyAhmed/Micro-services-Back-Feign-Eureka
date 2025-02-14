package com.omicrone.exceptions;


import feign.Response;
import feign.codec.ErrorDecoder;


public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoqueur, Response reponse) {

        if(reponse.status() == 400 ) {
            throw new AdressBadRequestException(
                    "Requête incorrecte "
            );
        }

        return defaultErrorDecoder.decode(invoqueur, reponse);
    }

}
