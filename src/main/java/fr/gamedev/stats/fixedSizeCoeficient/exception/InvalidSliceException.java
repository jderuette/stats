package fr.gamedev.stats.fixedSizeCoeficient.exception;

/**
 * When the slice is not valid.
 * @author djer13
 */
public class InvalidSliceException extends RuntimeException {

    /** serialVersionUID. */
    private static final long serialVersionUID = 6955668769558520245L;

    public InvalidSliceException(String msg) {
        super(msg);
    }

    public InvalidSliceException(String msg, Exception cause) {
        super(msg, cause);
    }

}
