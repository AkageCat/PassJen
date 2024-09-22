package org.example;

public class Password {
    private String value;
    private boolean visible;

    public Password() {
        this.value = "";
        this.visible = false;
    }

    public Password(String value) {
        this.value = value;
        this.visible = false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}