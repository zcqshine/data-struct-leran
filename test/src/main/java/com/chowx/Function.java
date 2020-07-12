package com.chowx;

import java.util.List;

/**
 * Created by zcqshine on 2017/2/20.
 */
public class Function {
    String id ;
    String functionReal;
    String functionFina;
    List<Function> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunctionReal() {
        return functionReal;
    }

    public void setFunctionReal(String functionReal) {
        this.functionReal = functionReal;
    }

    public String getFunctionFina() {
        return functionFina;
    }

    public void setFunctionFina(String functionFina) {
        this.functionFina = functionFina;
    }

    public List<Function> getList() {
        return list;
    }

    public void setList(List<Function> list) {
        this.list = list;
    }
}
