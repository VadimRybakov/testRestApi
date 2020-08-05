package ru.vadimio.TestRestApi.errors;

public enum Error {

    SMALL_NUMBER("very small result number"),
    BIG_NUMBER("very big result number"),
    NOT_INTEGER_VALUE("input value is not Integer");

    private String err;

    Error(String err){
        this.err = err;
    }

    public String getErr() {
        return err;
    }
}
