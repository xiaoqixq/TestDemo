package edu.controller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.GZIPOutputStream;

public class OutputSupportController {
    private static final String HTML_TYPE = "text/html";
    private static final String DEFAULT_ENCODING = "utf-8";

    private OutputStream buildGzipOutputStream(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Encoding", "gzip");
        response.setHeader("Vary", "Accept-Encoding");
        return new GZIPOutputStream(response.getOutputStream());
    }

    protected boolean isAcceptGzip(HttpServletRequest request) {
        String acceptEncoding = request.getHeader("Accept-Encoding");
        boolean hasGzip =  StringUtils.contains(acceptEncoding, "gzip");
        return hasGzip;
    }

    protected void textFileOutput(HttpServletResponse response, File file, boolean gzip) throws IOException {
        OutputStream output;
        if (gzip) {
            output = buildGzipOutputStream(response);
        }
        else {
            output = response.getOutputStream();
            response.setContentLength((int) file.length());
        }
        InputStream input = new FileInputStream(file);
        try {
            IOUtils.copy(input, output);
        }
        finally {
            output.close();
            input.close();
        }
    }

    protected void htmlFileOutput(HttpServletResponse response, File f, boolean gzip) throws IOException {
        response.setContentType(HTML_TYPE);
        response.setCharacterEncoding(DEFAULT_ENCODING);
        textFileOutput(response, f, gzip);
    }
}
