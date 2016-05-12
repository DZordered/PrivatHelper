package com.example.denis.privathelper.pojos;

import java.util.ArrayList;

/**
 * Created by denis on 5/12/16.
 */
public class TerminalResponse {

    public final ArrayList<TerminalDevice> devices;

    public TerminalResponse(ArrayList<TerminalDevice> devices) {
        this.devices = devices;
    }
}
