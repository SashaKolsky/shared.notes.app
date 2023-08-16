package com.dictality.front;

public class Front implements FrontBase {

    private final FrontBase[] fronts;

    public Front(FrontBase... fronts) {
        this.fronts = fronts;
    }

    @Override
    public void start() throws Exception {
        for (FrontBase front : fronts) {
            front.start();
        }
    }
}
