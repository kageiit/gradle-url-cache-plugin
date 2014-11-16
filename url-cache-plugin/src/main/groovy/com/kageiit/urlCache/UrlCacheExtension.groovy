package com.kageiit.urlCache

import java.security.MessageDigest

public final class UrlCacheExtension {
    private static final String FILE_NAME = "content"
    private static final MessageDigest MD5 = MessageDigest.getInstance("MD5")

    private final String cacheDir
    private final boolean isOffline

    public UrlCacheExtension(String cacheDir, boolean isOffline) {
        this.cacheDir = cacheDir
        this.isOffline = isOffline
    }

    public final File get(String url) {
        File contentDir = new File(cacheDir, generateMD5(url))
        contentDir.mkdirs()

        File content = new File(contentDir, FILE_NAME)
        content.createNewFile()

        if (!isOffline) {
            try {
                content.setBytes(new URL(url).getBytes())
            } catch (Exception ignored) {
            }
        }
        return content
    }

    private static String generateMD5(String s) {
        MD5.reset()
        MD5.update(s.bytes);
        return new BigInteger(1, MD5.digest()).toString(16).padLeft(32, '0')
    }
}
