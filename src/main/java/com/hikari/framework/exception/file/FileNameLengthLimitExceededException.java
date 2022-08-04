package com.hikari.framework.exception.file;

import java.io.Serial;
import java.util.Arrays;

public class FileNameLengthLimitExceededException extends FileException {
    @Serial
    private static final long serialVersionUID = 3348492051489853200L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", Arrays.toString(new Object[]{defaultFileNameLength}));
    }
}
