package com.example.denis.privathelper.pojos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 5/12/16.
 */
public class TerminalResponse {

    public final List<TerminalDevice> devices;

    public TerminalResponse(List<TerminalDevice> devices) {
        this.devices = devices;
    }
}
