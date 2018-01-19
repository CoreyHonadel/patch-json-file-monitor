package com.coreyhonadel.patchjsonfileserver;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Initializing Patch Server...\n");
		Thread.sleep(750);
		System.out.println("Scrubbing the Deck...\n");
		Thread.sleep(750);
		System.out.println("Sharpening the Blades...\n");
		Thread.sleep(750);
		System.out.println("Aligning the Cannons...\n");
		Thread.sleep(750);
		System.out.println("Hoisting the Flag!");
		printPirate();
		Thread.sleep(750);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter location where the patch json files are stored: ");

		String dirLocation = scanner.next();
		if (!dirLocation.endsWith("/")) {
			dirLocation += "/";
		}

		System.setProperty("location" , dirLocation);
		scanner.close();

		SpringApplication.run(SpringBootWebApplication.class, args);

	}

	private static void printPirate() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++##++++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++#+++++");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++#+++#");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++#++##");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++#+#");
		System.out.println("++++++++++++++++++'`        :++++++++++++++++#+###");
		System.out.println("++++++++++++++++.              ++#+++++++++++##+#+");
		System.out.println("++++++++++++++:                  +++++++#+++######");
		System.out.println("+++++++++++++                     +#+++++++#######");
		System.out.println("++++++++++++                       +++++#+++######");
		System.out.println("++++++++++#                         +#+++#########");
		System.out.println("#++++++#++.                          ++##+########");
		System.out.println("#++#++++++                           +++#+########");
		System.out.println("######+++`                            #+#+########");
		System.out.println("#####++++                             ##+#########");
		System.out.println("#####+##'                             '###########");
		System.out.println("########`                             .###########");
		System.out.println("#######+                               ###########");
		System.out.println("#######+                               +##########");
		System.out.println("#######+                               ###########");
		System.out.println("#######+                               ###########");
		System.out.println("########        `,.           `        ###########");
		System.out.println("########`     #######      ####       `###########");
		System.out.println("########;    #########     #### ##+   ,###########");
		System.out.println("########+   +#########     ###+'###   ;###########");
		System.out.println("########+   ##########     `   ####   '###########");
		System.out.println("#########   #########+         ####   '###########");
		System.out.println("#########   #########         #####   ;###########");
		System.out.println("#########   ,#######;      ;#+#####   :###########");
		System.out.println("#########    ######'   #+  ````       :###########");
		System.out.println("#########     #++#    '++`            ;###########");
		System.out.println("#########,            #+#+            +###########");
		System.out.println("#########+            +`:#            ############");
		System.out.println("##########...``       #.':          `+############");
		System.out.println("############';;:.````         `,,:'+##############");
		System.out.println("#################+...``   ````+###################");
		System.out.println("#################+....``````.::##########@########");
		System.out.println("#################### .`  `,.;##########   ;#######");
		System.out.println("######+  ;#######+ ##';#;+`@#  #######.    #######");
		System.out.println("######    '######: `#  ;#   #  #######     #######");
		System.out.println("######     ######'  :   #   #  ######`    `#######");
		System.out.println("######;    `###### `.   #   + +#####'      `######");
		System.out.println("######      :#######;   #   +######.        ######");
		System.out.println("#####;        ;##+###;,;#''+####;`         `######");
		System.out.println("#####+           .##########@'`      .':  `+######");
		System.out.println("######.` `;;         ,+###+`       :##############");
		System.out.println("#######+######'`        `       ,##########@######");
		System.out.println("#################+.           '@##########   #####");
		System.out.println("#####' ,#########+.              ,#######    `####");
		System.out.println("#####    #####'`        ':`          ````    ;####");
		System.out.println("#####,     ``        ;#######;     `````     #####");
		System.out.println("###### `   ```    ,##############:`````     `#####");
		System.out.println("######;```    .'######################;      #####");
		System.out.println("######`  ` `############################     #####");
		System.out.println("######'` `,##############################   ,#####");
		System.out.println("#######`  ################################''######");
		System.out.println("########:#########################################");
		System.out.println("##################################################");
		System.out.println("##################################################");
		System.out.println("##################################################");
	}

}