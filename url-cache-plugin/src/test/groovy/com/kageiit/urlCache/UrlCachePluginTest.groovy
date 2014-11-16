package com.kageiit.urlCache

import org.junit.Test

class UrlCachePluginTest {

    @Test
    public void urlCache_created() {
        File.createTempDir().with {
            deleteOnExit()
            UrlCacheExtension urlCacheExtension = new UrlCacheExtension(absolutePath, true)
            File content = urlCacheExtension.get("test")

            assert content.name.equals("content")
            assert content.parentFile.name.equals("098f6bcd4621d373cade4e832627b4f6")
            assert content.text.empty
        }
    }
}
