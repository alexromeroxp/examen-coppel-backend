package com.app.backend.DTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtilDTO {

    public static <T> ResponseEntity<Response<T>> generateSuccessResponse(T data) {
        Response<T> successResponse;
        if (data instanceof String) {
           successResponse = new Response<>(new Meta("OK"), new Message((String) data));
        }else{
            successResponse = new Response<>(new Meta("OK"), data);
        }
        return ResponseEntity.ok(successResponse);
    }

    public static ResponseEntity<Response<Object>> generateErrorResponse(String message) {
        Response<Object> errorResponse = new Response<>(new Meta("FAILURE"), new Message(message));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class Response<T> {
        private Meta meta;
        private Object data;

        public Response(Meta meta, Object data) {
            this.meta = meta;
            this.data = data;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    public static class Meta {
        private String status;

        public Meta(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class Data<T> {
        private T obj;

        public Data(T obj) {
            this.obj = obj;
        }

        public T getObj() {
            return obj;
        }

        public void setObj(T obj) {
            this.obj = obj;
        }
    }

    public static class Message {
        private String message;

        public Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
