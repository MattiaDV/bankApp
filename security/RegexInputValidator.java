package security;

public class RegexInputValidator {
    private boolean emailValidator(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Errore input");
        }

        email = email.trim();

        if (email.length() > 100) {
            throw new IllegalArgumentException("Errore input");
        }

        if (email.isEmpty() || !email.matches("[a-zA-Z0-9_.-]+@[a-zA-Z0-9_-]+\\.[a-z]+")) {
            throw new IllegalArgumentException("Errore input");
        }

        return true;
    }

    public boolean checkerDatas(String email, String pasw) {
        emailValidator(email);
        return true;
    }
}
