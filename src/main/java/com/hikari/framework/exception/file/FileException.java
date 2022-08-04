package com.hikari.framework.exception.file;

import com.hikari.framework.exception.SimpleException;

import java.io.Serial;

public class FileException extends SimpleException {
    @Serial
    private static final long serialVersionUID = 3464389218036118573L;

    public FileException(String code, String message) {
        super(code, message, "file");
    }
}
