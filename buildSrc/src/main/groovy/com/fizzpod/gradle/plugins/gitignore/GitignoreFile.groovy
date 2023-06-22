package com.fizzpod.gradle.plugins.gitignore

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

class GitignoreFile {
    public static final String FILE_NAME = ".gitignore"

    def getContents(File folder) {
        def gitignoreFile = getIgnoreFile(folder);
        def contents = null;
        if (gitignoreFile.exists()) {
            contents = gitignoreFile.text;
        }
        return contents;
    }

    def write(File folder, def extension) {
        final def ignoreFile = Paths.get(folder.toPath().toString(), FILE_NAME).toFile()
        if(!extension.merge) {
            clearIgnoreFile(folder)
        }

        ignoreFile.withWriterAppend { out ->
            extension.ignores.each { out.println it }
        }

        extension.urls.each { url ->
            ignoreFile << new URL (url).getText()
        }
        dedupeLines(folder)
    }

    def clearIgnoreFile(File folder) {
        getIgnoreFile(folder).text = ''
    }

    def getIgnoreFile(def folder) {
        return new File(folder, FILE_NAME);
    }

    def dedupeLines(File folder) {
        def ignoreFile = getIgnoreFile(folder)
        //remove duplicates
        def uniqueLines = new HashSet<String>()
        def lines = new ArrayList<String>()
        ignoreFile.eachLine { line ->
            if("" == line) {
                lines.add(line)
            } else {
                if(uniqueLines.add(line)) {
                    lines.add(line)
                }
            }
        }
        ignoreFile.withWriter { out ->
            lines.each { out.println it }
        }
    }

}
