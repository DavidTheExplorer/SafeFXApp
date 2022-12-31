# SafeFXApp
This is a helper project for JavaFX that takes care of boilerplate code when an application cannot start.

What is currently offers is the ability to define a _Global Error Handler_ to be used while the application is starting.\
After calling `shutdownWithError` multiple things are done:
1. The error handler is called(the default behaviour displays an elegant descriptive message)
2. The app is stopped without any stopping logic.

## Usage

You need to extend `SafeApplication` instead of JavaFX's `Application`, and put your stop logic(if you have any) inside `onStop()`.\
You now have access to the protected `shutdownWithError(String[] message)` method which calls the global error handler and then shuts down the application.\
If you want a custom handler, you can implement `ErrorHandler` and pass your instance to the builder of the class.

## Example:
Here is the skeleton for an app that writes the user input to a file upon closing:
```java
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import dte.safefxapp.SafeApplication;
import javafx.stage.Stage;

public class LinesApplication extends SafeApplication
{
	private File targetFile;
	private final List<String> linesToWrite = new ArrayList<>();
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		this.targetFile = new File(getParameters().getRaw().get(0));
		
		if(!this.targetFile.exists())
			shutdownWithError("The provided file doesn't exist!");
		
		if(!this.targetFile.getName().endsWith(".txt"))
			shutdownWithError("The provided file must be a text file!");
		
		//stage setup goes here
	}
	
	@Override
	protected void onStop() throws Exception 
	{
		Files.write(this.targetFile.toPath(), this.linesToWrite, StandardOpenOption.APPEND);
	}
}
```
