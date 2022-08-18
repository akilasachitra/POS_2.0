package testAPI.api.user;

/**
 * todo
 */


public enum Role {
    ADMIN("ADMIN"),
    USER("USER"),
    MANAGER("MANA"),
    ACCOUNTANT("ACCT");
    String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
