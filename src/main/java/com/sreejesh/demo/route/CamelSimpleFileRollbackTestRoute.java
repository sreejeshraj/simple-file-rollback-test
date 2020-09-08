package com.sreejesh.demo.route;

import com.sreejesh.synchro.FileRollback;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@ConfigurationProperties(prefix="camel-demo-route")
@Data
@EqualsAndHashCode(callSuper=true)

public class CamelSimpleFileRollbackTestRoute extends RouteBuilder {

	// The value of this property is injected from application.properties based on the profile chosen.
	private String injectedName;
	
	@Override
	public void configure() throws Exception {

		// @formatter:off
		


		from("file://{{inputFolder}}?delay=10s&noop=true")
		.routeId("FileRollbackTestRoute")
		.process(exchange -> exchange.getUnitOfWork().addSynchronization(new FileRollback()))
		.log("***** header.CamelFileNameOnly: ${header.CamelFileNameOnly}")
//		.log("***** InputFolderToTestSedaRoute - exchangeProperty.myProperty:${exchangeProperty.myProperty}")
//		.log("***** InputFolderToTestSedaRoute - exchangeId:${exchangeId}")
		.to("file://{{outputFolder}}")
		.log(LoggingLevel.DEBUG, "**** Input File Pushed To Output Folder ***** :"+injectedName)
		.log(LoggingLevel.DEBUG, "*** header.CamelFileNameProduced: ${header.CamelFileNameProduced} ***")
//		.to("file://{{outputFolder2}}")
//		.log(LoggingLevel.DEBUG, "**** Input File Pushed To Output2 Folder ***** :"+injectedName)
//		.log(LoggingLevel.DEBUG, "*** header.CamelFileNameProduced: ${header.CamelFileNameProduced} ***")
		.throwException(new Exception("*** Custom Exception!!! ***"))
		;


		
		
		// @formatter:on

	}

}
