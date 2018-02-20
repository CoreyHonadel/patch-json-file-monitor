To run the application:

`java -jar <path-to-file>/patchserver-1.0.jar`

The application will ask you to point to a folder directory where the JSON files live, something like:

`Enter location where the patch json files are stored: 
 /Users/coreyhonadel/Desktop/defs`
 
Currently, it will run on any available port it finds. To set it to a specific port where it will always run, launch the application with the following command `--server.port=<port>`.

Note, the *"lastModified"* variable does not need to be set and will auto populate based on the file's modified time.

Java 8 is required.
