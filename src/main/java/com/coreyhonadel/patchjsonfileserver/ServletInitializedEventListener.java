package com.coreyhonadel.patchjsonfileserver;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by coreyhonadel on 3/10/17.
 */
@Component
public class ServletInitializedEventListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

	@Override
	public void onApplicationEvent(final EmbeddedServletContainerInitializedEvent event) {

		int port = event.getEmbeddedServletContainer().getPort();
		System.out.println("\nApplication is available for the following endpoints:");
		System.out.println("http://localhost:" + port + "/software");
		System.out.println("http://localhost:" + port + "/software/{nameIds}");
		System.out.println("http://localhost:" + port + "/patch/{nameId}\n\n");

	}

}
