package com.rock.publish;

import com.rock.annnoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnsafePublish {

    /**
     * 发布对象
     */
    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
        //别的线程可以通过获取数组来改变数组的值，所以是不安全的
        unsafePublish.getStates()[0]="E";
        log.info("{}",Arrays.toString(unsafePublish.getStates()));
    }

}
