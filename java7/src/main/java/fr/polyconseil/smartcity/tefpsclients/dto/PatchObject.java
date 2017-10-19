package fr.polyconseil.smartcity.tefpsclients.dto;


import javax.annotation.Nullable;

public class PatchObject {

    private Operation op;

    private String path;

    @Nullable
    private Object index;

    private Object value;

    public enum Operation {
        add,
        replace,
        remove
    }

    public Operation getOp() {
        return op;
    }

    public void setOp(Operation op) {
        this.op = op;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Nullable
    public Object getIndex() {
        return index;
    }

    public void setIndex(@Nullable Object index) {
        this.index = index;
    }

}
