package com.alkemy.ong.common.exception.handler;

import com.alkemy.ong.common.exception.error.ApplicationErrorCode;
import com.alkemy.ong.common.exception.error.ErrorDetails;
import com.alkemy.ong.common.exception.error.ErrorLocation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public abstract sealed class AbstractExceptionHandler permits GlobalExceptionHandler {

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MissingServletRequestPartException.class,
            BindException.class,
            ConstraintViolationException.class,
            NoHandlerFoundException.class,
            AsyncRequestTimeoutException.class,
            ProtocolException.class,
            ResourceAccessException.class,
            ServletException.class,
            ClientAbortException.class,
            InternalAuthenticationServiceException.class,
            BadCredentialsException.class,
            IllegalArgumentException.class,
            HttpClientErrorException.class})
    @Nullable
    public final ResponseEntity<Object> handleException(Throwable throwable, WebRequest request) throws Throwable {
        log.error("Response Entity Exception due " + throwable, throwable);
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        if (throwable instanceof HttpRequestMethodNotSupportedException e) {
            status = HttpStatus.METHOD_NOT_ALLOWED;
            return this.handleHttpRequestMethodNotSupported(e, headers, status, request);
        } else if (throwable instanceof HttpMediaTypeNotSupportedException e) {
            status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
            return this.handleHttpMediaTypeNotSupported(e, headers, status, request);
        } else if (throwable instanceof HttpMediaTypeNotAcceptableException e) {
            status = HttpStatus.NOT_ACCEPTABLE;
            return this.handleHttpMediaTypeNotAcceptable(e, headers, status, request);
        } else if (throwable instanceof MissingPathVariableException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return this.handleMissingPathVariable(e, headers, status, request);
        } else if (throwable instanceof MissingServletRequestParameterException e) {
            status = HttpStatus.BAD_REQUEST;
            return this.handleMissingServletRequestParameter(e, headers, status, request);
        } else if (throwable instanceof ServletRequestBindingException e) {
            status = HttpStatus.BAD_REQUEST;
            return this.handleServletRequestBindingException(e, headers, status, request);
        } else if (throwable instanceof ConversionNotSupportedException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return this.handleConversionNotSupported(e, headers, status, request);
        } else if (throwable instanceof TypeMismatchException e) {
            status = HttpStatus.BAD_REQUEST;
            return this.handleTypeMismatch(e, headers, status, request);
        } else if (throwable instanceof HttpMessageNotReadableException e) {
            status = HttpStatus.BAD_REQUEST;
            return this.handleHttpMessageNotReadable(e, headers, status, request);
        } else if (throwable instanceof HttpMessageNotWritableException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return this.handleHttpMessageNotWritable(e, headers, status, request);
        } else if (throwable instanceof IllegalArgumentException e) {
            status = HttpStatus.BAD_REQUEST;
            return this.handleIllegalArgumentException(e, headers, status, request);
        } else if (throwable instanceof HttpClientErrorException e) {
            return this.handleHttpClientErrorException(e, headers, request);
        } else if (throwable instanceof MissingServletRequestPartException e) {
            status = HttpStatus.BAD_REQUEST;
            return this.handleMissingServletRequestPart(e, headers, status, request);
        } else if (throwable instanceof InternalAuthenticationServiceException e) {
            status = HttpStatus.BAD_REQUEST;
            return this.handleInternalAuthenticationServiceException(e, headers, status, request);
        } else if (throwable instanceof NoHandlerFoundException e) {
            status = HttpStatus.NOT_FOUND;
            return this.handleNoHandlerFoundException(e, headers, status, request);
        } else if (throwable instanceof AsyncRequestTimeoutException || throwable instanceof ProtocolException || throwable instanceof ServletException || throwable instanceof ClientAbortException || throwable instanceof SocketTimeoutException || throwable instanceof RestClientException) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
            return this.handleServiceUnavailableException((Exception) throwable, headers, status, request);
        } else if (throwable instanceof ConstraintViolationException e) {
            return this.handleConstraintViolationException(e, headers);
        } else if (throwable instanceof BindException e) {
            return this.handleBindException(e, headers);
        } else {
            throw throwable;
        }
    }

    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling HttpRequestMethodNotSupported with status={}", status);
        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if (!CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }

        final String message = ApplicationErrorCode.METHOD_NOT_CURRENTLY_ALLOWED.getDefaultMessage() + ". Unable to find method [" + ex.getMethod() + "], supported methods [" + ex.getSupportedHttpMethods() + "].";
        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.METHOD_NOT_CURRENTLY_ALLOWED, message);
    }

    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling HttpMediaTypeNotSupported with status={}", status);
        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
            if (request instanceof ServletWebRequest servletWebRequest) {
                if (HttpMethod.PATCH.equals(servletWebRequest.getHttpMethod())) {
                    headers.setAcceptPatch(mediaTypes);
                }
            }
        }

        final String message = ApplicationErrorCode.MEDIA_TYPE_NOT_SUPPORTED.getDefaultMessage() + " Supported media-types [" + ex.getSupportedMediaTypes() + "].";
        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.MEDIA_TYPE_NOT_SUPPORTED, message);
    }

    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling HttpMediaTypeNotAcceptable with status={}", status);

        final String message = ApplicationErrorCode.MEDIA_TYPE_NOT_CURRENTLY_ALLOWED.getDefaultMessage() + " Allowed media-types [" + ex.getSupportedMediaTypes() + "].";
        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.MEDIA_TYPE_NOT_CURRENTLY_ALLOWED, message);
    }

    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling eMissingPathVariable with status={}", status);

        final String message = ApplicationErrorCode.MISSING_PATH_VARIABLE.getDefaultMessage() + " Missing variable [" + ex.getVariableName() + "].";
        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.MISSING_PATH_VARIABLE, message);
    }

    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling MissingServletRequestParameter with status={}", status);

        final String message = ApplicationErrorCode.MISSING_REQUEST_PARAMETERS.getDefaultMessage() + " Missing parameter [" + ex.getParameterName() + "] with expected type [" + ex.getParameterType() + "].";
        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.MISSING_REQUEST_PARAMETERS, message);
    }

    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling ServletRequestBinding with status={}", status);

        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.REQUEST_BINDING, null);
    }

    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling ConversionNotSupported with status={}", status);

        final String message = ApplicationErrorCode.CONVERTION_NOT_SUPPORTED.getDefaultMessage() + " The offending value [" + ex.getValue() + "] couldn't be converted.";
        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.CONVERTION_NOT_SUPPORTED, message);
    }

    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling handleTypeMismatch with status={}", status);

        final String message = ApplicationErrorCode.TYPE_MISMATCH.getDefaultMessage() + " The offending value [" + ex.getValue() + "] couldn't be converted.";
        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.TYPE_MISMATCH, message);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling HttpMessageNotReadable with status={}", status);

        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.MESSAGE_NOT_READABLE, null);
    }

    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling HttpMessageNotWritable with status={}", status);

        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.MESSAGE_NOT_WRITABLE, null);
    }

    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling IllegalArgument with status={}", status);

        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.ILLEGAL_ARGUMENT, null);
    }

    protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, HttpHeaders headers, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling HttpClientError with status={}", ex.getStatusCode());

        final String message = ApplicationErrorCode.HTTP_CLIENT_ERROR.getDefaultMessage() + " Retrieved status [" + ex.getStatusText() + "] with response [" + ex.getResponseBodyAsString() + "].";
        return this.handleExceptionInternal(ex, headers, ex.getStatusCode(), request, ApplicationErrorCode.HTTP_CLIENT_ERROR, message);
    }

    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling MissingServletRequestPart with status={}", status);

        final String message = ApplicationErrorCode.HTTP_CLIENT_ERROR.getDefaultMessage() + " The part [" + ex.getRequestPartName() + "] of the multipart is missing.";
        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.MISSING_REQUEST_PART, message);
    }

    protected ResponseEntity<Object> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling InternalAuthenticationService with status={}", status);

        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.BAD_CREDENTIALS, null);
    }

    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling NoHandlerFound with status={}", status);

        final String message = ApplicationErrorCode.NOT_HANDLER_FOUND.getDefaultMessage() + " Unable to handle [" + ex.getHttpMethod() + " " + ex.getRequestURL() + "].";
        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.NOT_HANDLER_FOUND, message);
    }

    protected ResponseEntity<Object> handleServiceUnavailableException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling ServiceUnavailable with status={}", status);

        return this.handleExceptionInternal(ex, headers, status, request, ApplicationErrorCode.SERVICE_UNAVAILABLE, null);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request,
                                                             ApplicationErrorCode code, @Nullable String customMessage) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
        }

        final String description = customMessage != null ? customMessage : code.getDefaultMessage();

        List<ErrorDetails> errors = List.of(ErrorDetails.builder().code(code).detail(description).build());

        return ResponseEntity.status(status).headers(headers).body(errors);
    }

    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, HttpHeaders headers) {
        List<ErrorDetails> errors = ex.getConstraintViolations().stream()
                .map(constraintViolation -> ErrorDetails.builder()
                        .code(ApplicationErrorCode.INVALID_FIELD_VALUE)
                        .detail(constraintViolation.getMessage())
                        .field(constraintViolation.getPropertyPath().toString())
                        .location(ErrorLocation.BODY)
                        .build()
                ).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(errors);
    }

    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers) {
        List<ErrorDetails> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ErrorDetails.builder()
                        .code(ApplicationErrorCode.INVALID_FIELD_VALUE)
                        .detail(fieldError.getDefaultMessage())
                        .field(fieldError.getField())
                        .location(ErrorLocation.BODY)
                        .build()
                ).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(errors);
    }
}
