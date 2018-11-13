package br.com.michelmilezzi.yapott.exception;

public class YapottException extends Exception {

    private static final long serialVersionUID = -8745228441342634912L;

    public YapottException(String message) {
        super(message);
    }

    public YapottException(String message, Exception e) {
        super(message, e);
    }
    
}
