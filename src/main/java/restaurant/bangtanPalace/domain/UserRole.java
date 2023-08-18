package restaurant.bangtanPalace.domain;

public enum UserRole {
    ADMIN("Admin"),
    USER("User");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
