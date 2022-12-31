package dte.safefxapp;

import dte.safefxapp.errorhandlers.AlertErrorHandler;
import dte.safefxapp.errorhandlers.ErrorHandler;
import javafx.application.Application;
import javafx.application.Platform;

public abstract class SafeApplication extends Application
{
	private final ErrorHandler errorHandler;

	private boolean startupError = false;

	public SafeApplication(ErrorHandler errorHandler) 
	{
		this.errorHandler = errorHandler;
	}

	public SafeApplication()
	{
		this(AlertErrorHandler.INSTANCE);
	}

	@Override
	public void stop() throws Exception
	{
		if(this.startupError) 
			return;

		onStop();
	}

	/**
	 * The safe replacement for {@link Application#stop()} that child classes have to override.
	 * If the application was shut down using {@link #shutdownWithError(String...)} - this method does not run.
	 * 
	 * @throws Exception Adhering {@link #stop()} signature.
	 */
	protected void onStop() throws Exception {}

	/**
	 * Shuts down the application without calling {@link #onStop()}, while an indicative message is displayed to the user.
	 * 
	 * @param errorMessage The message to display to the user.
	 * @see #onStop()
	 */
	protected void shutdownWithError(String... errorMessage) 
	{
		this.startupError = true;
		this.errorHandler.handle(errorMessage);

		Platform.exit();
		System.exit(1);
	}
}
