package com.sreejesh.synchro;

import org.apache.camel.Exchange;
import org.apache.camel.spi.Synchronization;
import org.apache.camel.util.FileUtil;

import java.io.File;

public class DummySync implements Synchronization {

    @Override
    public void onFailure(Exchange exchange) {
        System.out.println("*** "+Thread.currentThread().getName()+":In DummySync.onFailure() ***");
    }

    @Override
    public void onComplete(Exchange exchange) {
        System.out.println("*** "+Thread.currentThread().getName()+":In DummySync.onComplete() ***");
    }
}
