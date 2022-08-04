package com.hikari.framework.exception.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import java.io.Serial;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class InvalidExtensionException extends FileUploadException {

    @Serial
    private static final long serialVersionUID = 1197276596244990392L;

    private String[] allowedExtension;
    private String extension;
    private String filename;

    public InvalidExtensionException(final String[] allowedExtension, final String extension, final String filename) {
        super("文件[" + filename + "]后缀[" + extension + "]不正确，请上传" + Arrays.toString(allowedExtension) + "格式");
        this.allowedExtension = allowedExtension;
        this.extension = extension;
        this.filename = filename;
    }

    public static class InvalidImageExtensionException extends InvalidExtensionException {
        @Serial
        private static final long serialVersionUID = 1L;

        public InvalidImageExtensionException(final String[] allowedExtension, final String extension, final String filename) {
            super(allowedExtension, extension, filename);
        }
    }

    public static class InvalidFlashExtensionException extends InvalidExtensionException {
        @Serial
        private static final long serialVersionUID = 1L;

        public InvalidFlashExtensionException(final String[] allowedExtension, final String extension, final String filename) {
            super(allowedExtension, extension, filename);
        }
    }

    public static class InvalidMediaExtensionException extends InvalidExtensionException {
        @Serial
        private static final long serialVersionUID = 1L;

        public InvalidMediaExtensionException(final String[] allowedExtension, final String extension, final String filename) {
            super(allowedExtension, extension, filename);
        }
    }

    public static class InvalidVideoExtensionException extends InvalidExtensionException {
        @Serial
        private static final long serialVersionUID = 1L;

        public InvalidVideoExtensionException(final String[] allowedExtension, final String extension, final String filename) {
            super(allowedExtension, extension, filename);
        }
    }
}
