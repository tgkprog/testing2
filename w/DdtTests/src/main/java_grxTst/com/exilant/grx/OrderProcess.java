package com.exilant.grx;

import com.apple.ist.grx.enums.OrderStatus;
import com.apple.ist.grx.processor.request.CancelOrderRequest;
import com.apple.ist.grx.processor.request.CreateOrderRequest;
import com.apple.ist.grx.processor.response.CancelOrderConfirmation;
import com.apple.ist.grx.processor.response.CreateOrderOutput;
import com.apple.ist.grx.security.request.GRXAuthenticationRequest;
import com.apple.ist.grx.security.response.GRXAuthenticationResponse;
import org.s2n.ddt.agent.AgentThread;
import org.s2n.ddt.bean.UtlProps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class OrderProcess {
	static long cnt = 0L;
	static Map<String, CancelOrderConfirmation> cancelmockData = new HashMap();
	static Map<String, CreateOrderOutput> createmockData = new HashMap();


	private static UtlProps props = null;
	

	static {
		
		
		props = new UtlProps(new File("/data/ddt/config/testApp.properties"));

		CancelOrderConfirmation output = new CancelOrderConfirmation();

		output.setAgreementId(Long.parseLong(props.getProperty("device1.agreementId")));
		output.setApplecareSalesDate(new Date(props.getProperty("device1.appleCareSalesDate")));
		output.setCancelOrderStatus(Boolean.parseBoolean(props.getProperty("device1.cancelOrderStatus")));
		output.setDeviceId(props.getProperty("device1.deviceId"));
		output.setEligible(Boolean.parseBoolean(props.getProperty("device1.eligible")));

		output.setOrderStatus(OrderStatus.PRIMARY_CANCELLED);
		output.setPartNumber(props.getProperty("device1.partNumber"));

		output.setPoNumber(props.getProperty("device1.poNumber"));
		output.setSecondarySerialNumber(props.getProperty("device1.secondarySerialNumber"));
		output.setSerialNumber(props.getProperty("device1.serialNumber"));

		cancelmockData.put("DeviceId1", output);

		output = new CancelOrderConfirmation();
		output.setAgreementId(Long.parseLong(props.getProperty("device2.agreementId")));
		output.setApplecareSalesDate(new Date(props.getProperty("device2.appleCareSalesDate")));
		output.setCancelOrderStatus(Boolean.parseBoolean(props.getProperty("device2.cancelOrderStatus")));
		output.setDeviceId(props.getProperty("device2.deviceId"));
		output.setEligible(Boolean.parseBoolean(props.getProperty("device2.eligible")));

		output.setOrderStatus(OrderStatus.PRIMARY_CANCELLED);
		output.setPartNumber(props.getProperty("device2.partNumber"));

		output.setPoNumber(props.getProperty("device2.poNumber"));
		output.setSecondarySerialNumber(props.getProperty("device2.secondarySerialNumber"));
		output.setSerialNumber(props.getProperty("device2.serialNumber"));

		cancelmockData.put("DeviceId2", output);

		output = new CancelOrderConfirmation();
		output.setAgreementId(Long.parseLong(props.getProperty("device3.agreementId")));
		output.setApplecareSalesDate(new Date(props.getProperty("device3.appleCareSalesDate")));
		output.setCancelOrderStatus(Boolean.parseBoolean(props.getProperty("device3.cancelOrderStatus")));
		output.setDeviceId(props.getProperty("device3.deviceId"));
		output.setEligible(Boolean.parseBoolean(props.getProperty("device3.eligible")));

		output.setOrderStatus(OrderStatus.PRIMARY_CANCELLED);
		output.setPartNumber(props.getProperty("device3.partNumber"));

		output.setPoNumber(props.getProperty("device3.poNumber"));
		output.setSecondarySerialNumber(props.getProperty("device3.secondarySerialNumber"));
		output.setSerialNumber(props.getProperty("device3.serialNumber"));

		cancelmockData.put("DeviceId3", output);

		CreateOrderOutput createOrderOutput = new CreateOrderOutput();
		createOrderOutput.setAgreementId(props.getProperty("createOrder.agreementId"));
		createOrderOutput.setAppleCareSalesDate(props.getProperty("createOrder.appleCareSalesDate"));
		createOrderOutput.setCoverageDurationStatement(props.getProperty("createOrder.coverageDurationStatement"));
		createOrderOutput.setEligible(Boolean.parseBoolean(props.getProperty("createOrder.eligible")));
		createOrderOutput.setPurchaseOrderNumber(props.getProperty("createOrder.purcheseOrderNumber"));
		createOrderOutput.setPartNumber(props.getProperty("createOrder.partNumber"));
		createmockData.put("PO1", createOrderOutput);
	}

	static Map<String, GRXAuthenticationResponse> authenticatedResponses = new HashMap();

	@WebMethod
	public GRXAuthenticationResponse authenticate(GRXAuthenticationRequest authenticationRequest) {
		GRXAuthenticationResponse response = new GRXAuthenticationResponse();
		System.out.println("authenticate");
		String sesId = null;
		if ((props.getProperty("userName").equals(authenticationRequest.getUserId())) && (props.getProperty("password").equals(authenticationRequest.getPassWord()))
				&& (props.getProperty("shipTo").equals(authenticationRequest.getShipTo()))) {
			long l1 = 1L;
			long l2 = 33L;

			sesId = UUID.randomUUID().toString();
			System.out.println("inside authentication");

			response.setPersonId(Long.parseLong((props.getProperty("responseID"))));
			response.setSessionId(sesId);
		}
		if (sesId != null) {
			authenticatedResponses.put(sesId, response);
		}
		return response;
	}

	public static GRXAuthenticationResponse getAuth(String id) {
		return (GRXAuthenticationResponse) authenticatedResponses.get(id);
	}

	@WebMethod
	public CancelOrderConfirmation cancelOrder(CancelOrderRequest cancelOrderRequest) throws FileNotFoundException, IOException {
			
		
		CancelOrderConfirmation response = null;
		System.out.println("cancelOrder " + ++cnt + " :" + cancelOrderRequest.getDeviceId());
		if ((props.getProperty("shipTo").equals(cancelOrderRequest.getShipToCode())) && (props.getProperty("device1.deviceId").equals(cancelOrderRequest.getDeviceId()))) {
			response = (CancelOrderConfirmation) cancelmockData.get("DeviceId1");
		} else if (props.getProperty("device2.deviceId").equals(cancelOrderRequest.getDeviceId())) {
			response = (CancelOrderConfirmation) cancelmockData.get("DeviceId2");
		} else if (props.getProperty("device3.deviceId").equals(cancelOrderRequest.getDeviceId())) {
			response = (CancelOrderConfirmation) cancelmockData.get("DeviceId3");
		} else {
			response.setErrorMessage(props.getProperty("errMsg.deviceId"));
			return response;
		}
		return response;
	}


	@WebMethod
	public CreateOrderOutput createOrder(CreateOrderRequest createOrderRequest) {
		CreateOrderOutput response = null;
		System.out.println("createOrder");
		if (props.getProperty("createOrder.purcheseOrderNumber").equals(createOrderRequest.getPurchaseOrderNumber())) {
			response = (CreateOrderOutput) createmockData.get("PO1");
		}
		return response;
	}
	
	private static String getErrorMsg() {
		String path = System.getProperty("DDT_CANCELORDER_ERROR");
		if (path == null) {
			path = "/data/ddt/config/errormessages.properties";
		}
		
		return path;
	}
}