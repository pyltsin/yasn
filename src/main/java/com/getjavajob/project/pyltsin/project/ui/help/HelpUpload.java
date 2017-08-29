package com.getjavajob.project.pyltsin.project.ui.help;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * Created by Pyltsin on 10.01.2017. Algo8
 */
public class HelpUpload {
    public static InputStream getStreamFromUpload(HttpServletRequest request) {
        InputStream fileContent = null;
        Part filePart;
        try {
            filePart = request.getPart("picture");
            if (filePart == null) {
                return null;
            }
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            if (fileName == null || fileName.equals("")) {
                return null;
            }

            fileContent = filePart.getInputStream();

        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        return fileContent;
    }
}
