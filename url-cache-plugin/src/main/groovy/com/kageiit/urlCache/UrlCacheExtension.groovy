package com.kageiit.urlCache

import java.security.MessageDigest
import java.util.concurrent.TimeUnit

public final class UrlCacheExtension {
    private static final String FILE_NAME = "content"
    private static final MessageDigest MD5 = MessageDigest.getInstance("MD5")

    private final String cacheDir
    private final boolean isOffline
    private final boolean forceRefresh

    public UrlCacheExtension(String cacheDir, boolean isOffline, boolean forceRefresh) {
        this.cacheDir = cacheDir
        this.isOffline = isOffline
        this.forceRefresh = forceRefresh
    }

    public final File get(String url) {
        File contentDir = new File(cacheDir, generateMD5(url))
        contentDir.mkdirs()

        File content = new File(contentDir, FILE_NAME)

        boolean download
        if (isOffline) {
            download = false
        } else {
            download = forceRefresh || !content.exists() || (System.currentTimeMillis() - content.lastModified()) > TimeUnit.DAYS.toMillis(1)
        }

        if (!content.exists()) {
            content.createNewFile()
        }

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
