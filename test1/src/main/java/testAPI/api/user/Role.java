package testAPI.api.user;

/**
 * todo
 */


public enum Role {
    ADMIN("ADMIN"),
    DOCTOR("DOCTOR"),
    NURSE("NURSE"),
    ATTENDANT("ATTENDANT");
    String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
