package com.example.VCloud.Managers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.stream.Stream;

public class FileManager {
    private Path globalPath = Paths.get("src/main/resources/cloud");

    public FileManager() {}


    public JSONObject getDirectoryTree(String login){
        Path userDirectory = getUserPath(login);

        if( userDirectory != null){
            JSONObject dirObject = new JSONObject();
            dirObject.put("name", userDirectory.getFileName().toString());
            dirObject.put("type", "directory");

            JSONArray children = new JSONArray();

            try (Stream<Path> paths = Files.list(userDirectory)) {
                paths.forEach(path -> {
                    if (Files.isDirectory(path)) {
                        children.put(getDirectoryTree(String.valueOf(path)));
                    } else {
                        JSONObject fileObject = new JSONObject();
                        fileObject.put("name", path.getFileName().toString());
                        fileObject.put("type", "file");
                        children.put(fileObject);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            dirObject.put("children", children);
            return dirObject;
        }
        return null;
    }

    private Path getUserPath(String login) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-512");
            byte[] hashedBytes = digest.digest(login.getBytes(StandardCharsets.UTF_8));
            String dirName = Base64.getEncoder().encodeToString(hashedBytes);

            Path userPath = globalPath.resolve(dirName);

            if(!Files.exists(userPath)){
                Files.createDirectory(userPath);
            }
            return userPath;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
