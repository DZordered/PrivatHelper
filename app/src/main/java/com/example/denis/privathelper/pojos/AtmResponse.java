package com.example.denis.privathelper.pojos;

import java.util.List;


public class AtmResponse {
    public final List<AtmDevice> devices;

    public AtmResponse(List<AtmDevice> devices) {
        this.devices = devices;
    }
}
