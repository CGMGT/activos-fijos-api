package gt.com.tigo.fixed_assets.util.exception;

public class ResourcesNotFoundException extends Exception {

    public ResourcesNotFoundException() {
        super("No se han podido encontrar los recursos solicitados.");
    }

}
