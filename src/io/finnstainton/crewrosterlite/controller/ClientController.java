/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.controller;

import io.finnstainton.crewrosterlite.model.Client;
import io.finnstainton.crewrosterlite.model.Records;

/**
 *
 * @author finnstainton
 */
public class ClientController {
    private final Records<String, Client> clientRecords;
    
    public ClientController(Records clientRecords) {
        this.clientRecords = clientRecords;
    }
}
