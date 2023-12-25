package com.example.ProgrammingTechnology.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AttributeException extends RuntimeException{
    private String attrName;
    private Long entityId;

    public AttributeException(String message, String attrName, Long entityId) {
        super(message);
        this.attrName = attrName;
        this.entityId = entityId;
    }
}
