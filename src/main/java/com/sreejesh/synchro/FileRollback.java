package com.sreejesh.synchro;

import org.apache.camel.Exchange;
import org.apache.camel.spi.Synchronization;
import org.apache.camel.util.FileUtil;

import java.io.File;

public class FileRollback implements Synchronization {

    @Override
    public void onFailure(Exchange exchange) {
        String name = exchange.getIn().getHeader(Exchange.FILE_NAME_PRODUCED, String.class);
        FileUtil.deleteFile(new File(name));
//        FileUtil.removeDir(new File(name));
    }

    @Override
    public void onComplete(Exchange exchange) {

    }
}
