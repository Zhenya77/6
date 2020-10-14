package controller;

public interface CommandWithObject extends Commandable {
    boolean check(int arg);
    String whyFailed();
}
