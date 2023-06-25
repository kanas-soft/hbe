package com.kanas.fixtbe.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * Base DTO abstract class.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class BaseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;

    private UUID id;
}