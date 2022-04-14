package com.alkemy.ong.common.exception.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
@JsonPropertyOrder({"code", "detail", "field", "value", "location"})
public class ErrorDetails {

    /**
     * The unique and fine-grained application-level error code.
     */
    @NotNull ApplicationErrorCode code;

    /**
     * The human-readable description for an issue. Provide non-standard more granular error message.
     */
    @NotNull String detail;

    /**
     * The field that caused the error. If the field is in the body, set this value to the JSON pointer to that field.
     * Example: "field": {"$ref": "#/body-field"}
     */
    String field;

    /**
     * The value of the field that caused the error.
     */
    Object value;

    /**
     * The location of the field that caused the error. Value is `BODY`, `PATH`, `QUERY` or `HEADER`.
     */
    ErrorLocation location;

}
