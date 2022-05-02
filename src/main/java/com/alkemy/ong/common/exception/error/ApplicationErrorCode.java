package com.alkemy.ong.common.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationErrorCode {
    INVALID_FIELD_VALUE("The provided field is not valid."),
    RESOURCE_NOT_FOUND("The requested resource was not found."),
    BAD_REQUEST("The server can’t return a response due to an error on the client’s end."),
    ILLEGAL_ARGUMENT("Has been passed an illegal or inappropriate argument."),
    HTTP_CLIENT_ERROR("The request failed because a 4xx error was received."),
    BAD_CREDENTIALS("The server can’t return a response due to invalid credentials."),
    TOKEN_INVALID_OR_EXPIRED("The server can not return a response due to an invalid token or token expired."),
    INTERNAL_ERROR("There was an error on the server and the request could not be completed."),
    SERVICE_NOT_IMPLEMENTED("The requested service is not implemented in this api version."),
    SERVICE_UNAVAILABLE("The server is unavailable to handle this request right now. Please try again later."),
    METHOD_NOT_CURRENTLY_ALLOWED("The requested method is not allowed in this api version."),
    MEDIA_TYPE_NOT_SUPPORTED("The provided mediaType is not supported."),
    MEDIA_TYPE_NOT_CURRENTLY_ALLOWED("The provided mediaType is not allowed in this api version."),
    CONVERTION_NOT_SUPPORTED("No suitable editor or converter can be found for a provided property."),
    MISSING_PATH_VARIABLE("The URI template may not match the path variable name declared on the method parameter."),
    MISSING_REQUEST_PARAMETERS("The request body may be missing mandatory parameters."),
    REQUEST_BINDING("Fatal binding exception."),
    MESSAGE_NOT_READABLE("The request message could not be read."),
    MESSAGE_NOT_WRITABLE("The request message could not be written."),
    MISSING_REQUEST_PART("The request is not a multipart/form-data request."),
    NOT_HANDLER_FOUND("The server could not found a handler for the request."),
    TYPE_MISMATCH("A type mismatch occurred trying to set a property."),
    PARAMS_REQUIRED("The request body may be missing mandatory parameters."),
    ROLE_INVALID("The server can not return a response due to an invalid role"),
    UNAUTHORIZED("Wrong credentials."),
	RESOURCE_ALREADY_EXISTS("This resource already exists.");

    private final String defaultMessage;

}
