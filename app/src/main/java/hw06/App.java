package hw06;

import hw06.api.route.Handler;

import java.util.List;


public final class App {
    private final List<Handler> controllers;

    public App(List<Handler> controllers) {
        this.controllers = controllers;
    }

    public void start() {
        controllers.forEach(Handler::initializeEndpoints);
    }
}