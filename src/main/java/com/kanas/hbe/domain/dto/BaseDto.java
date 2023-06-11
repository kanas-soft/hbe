package com.kanas.hbe.domain.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Base DTO abstract class.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class BaseDto implements Serializable {

    private UUID id;
}