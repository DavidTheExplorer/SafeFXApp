package dte.safefxapp.errorhandlers;

@FunctionalInterface
public interface ErrorHandler
{
	void handle(String[] message);
}