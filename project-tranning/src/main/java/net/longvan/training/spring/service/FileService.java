package net.longvan.training.spring.service;

import java.io.IOException;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import net.longvan.training.spring.model.FileModel;

public interface FileService {
	String uploadFile(MultipartFile image) throws IllegalStateException, IOException;

	Set<String> listFilesUsingFilesList() throws IOException;

	FileModel checkFileName(String name);
}
