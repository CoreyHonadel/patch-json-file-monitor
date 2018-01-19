package com.coreyhonadel.patchjsonfileserver.objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

/**
 * Created by coreyhonadel on 3/13/17.
 */
@Component
public class SoftwareTitleCache {

	private HashMap<String, SoftwareTitle> softwareTitleByNameId;

	public SoftwareTitleCache() {
		softwareTitleByNameId = new HashMap<>();
	}

	public List<SoftwareTitle> getSoftwareTitles() {
		return softwareTitleByNameId.entrySet()
				.stream()
				.map(Map.Entry::getValue)
				.collect(Collectors.toList());
	}

	public List<SoftwareTitle> getSoftwareTitlesForNameIds(List<String> nameIds) {
		return softwareTitleByNameId.entrySet()
				.stream()
				.filter(mapEntry -> nameIds.contains(mapEntry.getValue().getNameId()))
				.map(Map.Entry::getValue)
				.collect(Collectors.toList());
	}

	public Optional<SoftwareTitle> getSoftwareTitlesForNameId(String nameId) {
		return softwareTitleByNameId.entrySet()
				.stream()
				.filter(mapEntry ->  nameId.equals(mapEntry.getValue().getNameId()))
				.map(Map.Entry::getValue)
				.findFirst();
	}

	public Optional<SoftwareTitle> getSoftwareTitleForFileName(String fileName) {
		return softwareTitleByNameId.entrySet()
				.stream()
				.filter(mapEntry -> fileName.equals(mapEntry.getValue().getFileName()))
				.map(Map.Entry::getValue)
				.findFirst();
	}

	public void addSoftwareTitleToCache(SoftwareTitle softwareTitle) {
		System.out.println("New " + softwareTitle.getName() + " found!");
		softwareTitleByNameId.put(softwareTitle.getNameId(), softwareTitle);
	}

	public void removeSoftwareTitleFromCacheByFileName(String fileName) {
		Optional<SoftwareTitle> softwareTitleOptional = getSoftwareTitleForFileName(fileName);

		if (softwareTitleOptional.isPresent()) {
			SoftwareTitle softwareTitle = softwareTitleOptional.get();
			System.out.println(softwareTitle.getName() + " removed");
			softwareTitleByNameId.remove(softwareTitle.getFileName());
		}
	}

}
