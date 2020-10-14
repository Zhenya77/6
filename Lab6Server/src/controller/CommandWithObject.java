package controller;

import dragon.Dragon;

public interface CommandWithObject extends Commandable{
    boolean check(int arg);
    String whyFailed();
}
