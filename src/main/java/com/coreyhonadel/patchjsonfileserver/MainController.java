package com.coreyhonadel.patchjsonfileserver;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coreyhonadel.patchjsonfileserver.objects.SoftwareTitle;
import com.coreyhonadel.patchjsonfileserver.objects.SoftwareTitleCache;

@RestController
public class MainController {

	@Autowired
	private SoftwareTitleCache softwareTitleCache;

	@RequestMapping(value = "/software", method = RequestMethod.GET)
	public List<SoftwareTitle> getAllTitles() {
		return softwareTitleCache.getSoftwareTitles();
	}

	@RequestMapping(value = "/software/{nameIds}", method = RequestMethod.GET)
	public List<SoftwareTitle> getTitleByNameIds(@PathVariable("nameIds") List<String> nameIds) {
		return softwareTitleCache.getSoftwareTitlesForNameIds(nameIds);
	}

	@RequestMapping(value = "/patch/{nameId}", method = RequestMethod.GET)
	public String getPatch(@PathVariable("nameId") String nameId) {
		Optional<SoftwareTitle> softwareTitleOptional = softwareTitleCache.getSoftwareTitlesForNameId(nameId);
		if (softwareTitleOptional.isPresent()) {
			return softwareTitleOptional.get().getData();
		}
		return new String();
	}

}