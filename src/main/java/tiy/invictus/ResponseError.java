package tiy.invictus;

/**
 * Created by Brice on 10/2/16.
 */
public class ResponseError implements Response{

    public String errorMessage;

    public ResponseError(String message) {
        this.errorMessage = message;
    }

    public ResponseError() {
    }

    public String getMessage() {
        return errorMessage;
    }

    public void setMessage(String message) {
        this.errorMessage = message;
    }


}
