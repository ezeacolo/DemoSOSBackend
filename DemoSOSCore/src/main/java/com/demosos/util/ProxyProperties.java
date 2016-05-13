package com.demosos.util;

import java.io.IOException;
import java.util.Properties;

public class ProxyProperties {

    private static final String CONFIG_FILE_NAME = "proxyconf.properties";
    private static final String PATH_UPLOAD_PHOTO = "path.upload.photo";
    private static final String PATH_UPLOAD_TMP_PHOTO = "path.upload.tmp.photo";
    private static final String URL_DOWNLOAD_PHOTO = "url.download.photo";
    private Properties properties;


    public ProxyProperties() {
        properties = new Properties();

        try {
            properties.load(ProxyProperties.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPathUploadPhoto() {
        return properties.getProperty(PATH_UPLOAD_PHOTO);
    }

    public String getPathUploadTmpPhoto() {
        return properties.getProperty(PATH_UPLOAD_TMP_PHOTO);
    }

    public String getUrlDownloadPhoto() {
        return properties.getProperty(URL_DOWNLOAD_PHOTO);
    }
}