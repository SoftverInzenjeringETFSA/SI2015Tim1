package Exceptions;

public class WrongInputException extends Exception{

	public WrongInputException(String poruka){
		super (poruka);
	}
}
