package pl.przydan.HelloServerApp;

class Lang {
    private Integer id;
    private String welcomeMessage;
    private String code;

    public Lang(Integer id, String welcomeMessage, String code) {
        this.id = id;
        this.welcomeMessage = welcomeMessage;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public String getWelcomeMsg() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
