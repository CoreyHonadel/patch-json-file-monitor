package com.coreyhonadel.patchjsonfileserver.services;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.coreyhonadel.patchjsonfileserver.objects.SoftwareTitle;
import com.coreyhonadel.patchjsonfileserver.objects.SoftwareTitleCache;

/**
 * Created by coreyhonadel on 3/10/17.
 */
@Service
public class FolderWatcherService {

	@Value("${location}")
	private String location;

	@Autowired
	private FileParserService fileParserService;

	@Autowired
	private SoftwareTitleCache softwareTitleCache;

	private Path path;
	private WatchService watchService;

	@Scheduled(initialDelayString = "0", fixedDelayString = "10000")
	public void startWatcher() throws InterruptedException {

		WatchKey key = watchService.poll();
		if (key != null) {
			key.pollEvents().stream().forEach(
					watchEvent -> processModifiedFile(((WatchEvent<Path>) watchEvent).context(), (WatchEvent.Kind) watchEvent.kind()));
			key.reset();
		}
	}

	private void processModifiedFile(Path eventPath, WatchEvent.Kind event) {
		if (StandardWatchEventKinds.ENTRY_CREATE.equals(event)
				|| StandardWatchEventKinds.ENTRY_MODIFY.equals(event)) {
			doSomething(eventPath);
		} else if (StandardWatchEventKinds.ENTRY_DELETE.equals(event)) {
			softwareTitleCache.removeSoftwareTitleFromCacheByFileName(eventPath.getFileName().toString());
		}
	}

	private void doSomething(Path eventPath) {
		// Get the software title from the event
		Optional<SoftwareTitle> softwareTitleOptional = fileParserService.parseFile(eventPath);

		if (softwareTitleOptional.isPresent()) {
			SoftwareTitle softwareTitle = softwareTitleOptional.get();
			softwareTitleCache.addSoftwareTitleToCache(softwareTitle);
		}
	}

	@PostConstruct
	private void setup() {
		try {
			System.out.println("Setting up File Watcher Service...\n");
			path = Paths.get(location);

			// Start by finding any existing software titles
			System.out.println("Scanning for existing files...\n");
			Files.list(path)
					.filter(Files::isRegularFile)
					.forEach(filePath -> doSomething(filePath.getFileName()));

			watchService = FileSystems.getDefault().newWatchService();
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
		} catch (IOException e) {
			System.out.println("Error starting watcher service " + e.getMessage());
		}
	}

	@PreDestroy
	public void killWatcher() {
		try {
			System.out.println("Killing the file watcher");
			watchService.close();
		} catch (IOException e) {
			// Ignore since thread is shutting down anyway
		}
	}
}
