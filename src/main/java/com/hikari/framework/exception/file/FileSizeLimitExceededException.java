package com.hikari.framework.exception.file;

import java.io.Serial;
import java.util.Arrays;

public class FileSizeLimitExceededException extends FileException {
    @Serial
    private static final long serialVersionUID = 9207566629565592354L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", Arrays.toString(new Object[]{defaultMaxSize}));
    }
}
