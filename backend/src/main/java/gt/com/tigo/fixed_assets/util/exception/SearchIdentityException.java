package gt.com.tigo.fixed_assets.util.exception;

public class SearchIdentityException extends Exception {

    public SearchIdentityException() {
        super("Error al recuperar la información del usuario.");
    }

    public SearchIdentityException(String message) {
        super(message);
    }

}
