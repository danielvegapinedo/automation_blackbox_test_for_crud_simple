package ca.danielvega.learning.seleniumforcrudsimple.pages;

public enum Address {
    LOGING("http://localhost:8080/crud_simple/login.html"),
    GRID("http://localhost:8080/crud_simple/grid_device.jsp"),
    CREATE("http://localhost:8080/crud_simple/create_device.jsp"),
    REMOVE("http://localhost:8080/crud_simple/remove_device.jsp"),
    UPDATE("http://localhost:8080/crud_simple/update_device.jsp"),
    DISPLAY("http://localhost:8080/crud_simple/display_device.jsp");
    
    private final String url;

    private Address(String url) {
        this.url = url;
    }
    
    public String getURL(){
        return this.url;
    }
}
