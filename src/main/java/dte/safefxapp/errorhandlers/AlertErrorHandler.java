package dte.safefxapp.errorhandlers;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertErrorHandler implements ErrorHandler
{
	public static final AlertErrorHandler INSTANCE = new AlertErrorHandler();
	
	@Override
	public void handle(String[] message)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Startup Error");
		alert.setContentText(Arrays.stream(message).collect(joining("\n")));
		alert.showAndWait();
	}
}