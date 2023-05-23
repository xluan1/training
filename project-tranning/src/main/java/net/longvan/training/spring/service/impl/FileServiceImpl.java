package net.longvan.training.spring.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.longvan.training.spring.model.FileModel;
import net.longvan.training.spring.repository.FileRepository;
import net.longvan.training.spring.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	public static final String PATH = "D:\\LongVan\\Upload\\image\\";

	@Autowired
	private FileRepository fileRepository;

	@Override
	public String uploadFile(MultipartFile file) throws IllegalStateException, IOException {
		String originFile = PATH + file.getOriginalFilename();
		if (listFilesUsingFilesList().contains(file.getOriginalFilename())) {
			return "File đã tồn tại!";
		} else {
			file.transferTo(new File(originFile));
			FileModel fileModel = new FileModel(file.getOriginalFilename(), originFile);
			fileRepository.insert(fileModel);
			return "Tải tập tin thành công!";
		}
	}

	public Set<String> listFilesUsingFilesList() throws IOException {
		try (Stream<Path> stream = Files.list(Paths.get(PATH))) {
			return stream.filter(file -> !Files.isDirectory(file)).map(Path::getFileName).map(Path::toString)
					.collect(Collectors.toSet());
		}
	}

	public FileModel checkFileName(String name) {
		FileModel check = fileRepository.checkFile(name);
		if (check != null) {
			return check;
		}
		return null;
	}
}
