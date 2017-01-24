package com.kageiit.urlCache

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.invocation.Gradle

class UrlCachePlugin implements Plugin<Project> {

    private static final String URL_CACHE = "urlCache"
    private static final String URL_CACHE_DIRECTORY = "url-cache"

    @Override
    void apply(Project project) {
        Gradle gradle = project.gradle
        project.extensions.add(URL_CACHE, new UrlCacheExtension(
                "${gradle.gradleUserHomeDir}/caches/${URL_CACHE_DIRECTORY}",
                gradle.startParameter.offline, gradle.startParameter.refreshDependencies))
    }
}
