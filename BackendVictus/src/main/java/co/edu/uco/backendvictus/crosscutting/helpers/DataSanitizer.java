package co.edu.uco.backendvictus.crosscutting.helpers;

/**
 * Utility class that centralizes input sanitization to avoid duplicated logic across layers.
 */
public final class DataSanitizer {

    private DataSanitizer() {
        // Utility class
    }

    /**
     * Delegates to {@link TextHelper#sanitize(String)} to remove dangerous characters and normalize text.
     *
     * @param rawValue raw input provided by clients
     * @return sanitized text safe to be stored in the domain
     */
    public static String sanitizeText(final String rawValue) {
        return TextHelper.sanitize(rawValue);
    }
}
