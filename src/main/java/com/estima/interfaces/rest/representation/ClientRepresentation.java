package com.estima.interfaces.rest.representation;

import lombok.Getter;

@Getter
public class ClientRepresentation {
    private Long id;
    private String title;
    private String contactName;
    private String contactPosition;
    private String phone;
}
