package fr.gamedev.stats.fixedSizeCoeficient.exception;

/**
 * When the rule is not valid.
 * @author djer13
 */
public class InvalidRuleException extends RuntimeException {

    /** serialVersionUID. */
    private static final long serialVersionUID = -6822239589222662973L;

    public InvalidRuleException(String msg) {
        super(msg);
    }

    public InvalidRuleException(String msg, Exception cause) {
        super(msg, cause);
    }

}
