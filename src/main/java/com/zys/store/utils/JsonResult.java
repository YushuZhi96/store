package com.zys.store.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class JsonResult<E>  implements Serializable {
    //state code
    private Integer state;
    //message
    private String message;

    private E data;
    public JsonResult(){

    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, E data){
        this.state = state;
        this.data = data;
    }

    public JsonResult(Throwable e){
        this.message = e.getMessage();
    }

}
