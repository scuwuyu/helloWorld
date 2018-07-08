package com.gongsi.mycoin.utils;

import com.gongsi.mycoin.core.ensure.Ensure;
import com.gongsi.mycoin.core.utils.IdGenerator;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 吴宇 on 2018-05-27.
 */
public class TestUtils {
    public static void main(String[] args) {



    }


    public void testId(){
        final Object lock = new Object();
        final ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();

        for (int i=0;i<10;i++){
            new Thread(()->{
                for (int j=0;j<100000;j++){
                    String id = IdGenerator.nextId();
                    synchronized (lock){
                        System.out.println(Thread.currentThread().getName()+"======"+id);
                        if (map.containsKey(id)){
                            Ensure.that(true).isFalse("已经存在了");
                        }
                        map.putIfAbsent(id,"");
                    }
                }
            },"Thread-"+i).start();
        }
    }
}
