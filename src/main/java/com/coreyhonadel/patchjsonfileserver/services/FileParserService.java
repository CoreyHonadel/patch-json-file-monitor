package com.coreyhonadel.patchjsonfileserver.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.coreyhonadel.patchjsonfileserver.JamfLastModifiedDateFormatter;
import com.coreyhonadel.patchjsonfileserver.objects.SoftwareTitle;

/**
 * Created by coreyhonadel on 3/12/17.
 */
@Service
public class FileParserService {

	@Value("${location}")
	private String location;

	@Autowired
	private JamfLastModifiedDateFormatter formatter;

	private ObjectMapper mapper;

	public FileParserService() {
		mapper = new ObjectMapper();
	}

	public Optional<SoftwareTitle> parseFile(Path path) {
		Optional<SoftwareTitle> softwareTitleOptional = Optional.empty();
		try {
			String fileContent = new String(Files.readAllBytes(Paths.get(location + path)));
			FileTime lastModified = Files.getLastModifiedTime(Paths.get(location + path));

			SoftwareTitle softwareTitle = mapper.readValue(fileContent, SoftwareTitle.class);
			softwareTitle.setFileName(path.toString());
			softwareTitle.setLastModified(Date.from(lastModified.toInstant()));

			// Update the files last modified since it is stored with 0s
			JsonNode node = mapper.readValue(fileContent, JsonNode.class);
			ObjectNode object = (ObjectNode) node;
			object.put("lastModified", formatter.convert(Date.from(lastModified.toInstant())));

			softwareTitle.setData(node.toString());

			softwareTitleOptional = Optional.of(softwareTitle);
		} catch (Exception e) {
			System.out.println("Unable to parse: " + path.getFileName());
		}

		return softwareTitleOptional;
	}
}
