class Token {
    private String group;
    private String value;

    public Token(String name, String value) {
        this.group = name;
        this.value = value;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String name) {
        this.group = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("<%s, `%s`>", this.group, this.value);
    }
}