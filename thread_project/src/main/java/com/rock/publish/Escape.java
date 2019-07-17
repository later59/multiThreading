package com.rock.publish;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Escape {

    private int thisCanBeEscape = 10;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
            new Escape();

    }
}
