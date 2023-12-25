package com.example.ProgrammingTechnology.responses;

import com.example.ProgrammingTechnology.exception.AttributeException;

public class AttrResponse{
    private String atrName;
    private Long id;
    private String message;
    public static AttrResponse fromEx(AttributeException e){
        AttrResponse resp = new AttrResponse();
        resp.atrName = e.getAttrName();
        resp.id = e.getEntityId();
        resp.message = e.getMessage();
        return resp;
    }
}