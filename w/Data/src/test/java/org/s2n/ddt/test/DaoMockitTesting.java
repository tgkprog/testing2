package org.s2n.ddt.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.s2n.ddt.dao.ApplicationDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.Application;
import org.s2n.ddt.service.impl.MainServiceImpl;

public class DaoMockitTesting {

	@Mock
	private ApplicationDao applicationDao;
	private MainServiceImpl mainService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mainService = new MainServiceImpl();
		mainService.setApplicationDao(applicationDao);
	}

	@Test
	public void fetchApplication() throws DataAccessException {
		Application application = getApplication();
		when(mainService.getApplicationDetailsById(1)).thenReturn(application);
		assertEquals("SampleSpringData", application.getAppName());
		assertEquals("SampleSpringData-Description", application.getDescription());
		assertEquals(getUser(), application.getCreatedBy());
		assertEquals(getDate(), application.getCreatedDateTime());
		assertEquals(getUser(), application.getUpdatedBy());
		assertEquals(getDate(), application.getUpdatedDateTime());
		verifyNoMoreInteractions(applicationDao);
	}

	private static String getUser() {
		return System.getenv("USER");
	}

	private static Date getDate() {
		return new Date(3213246546456L);
	}

	private Application getApplication() {
		Application application = new Application();
		application.setAppId(new BigDecimal(1));
		application.setAppName("SampleSpringData");
		application.setDescription("SampleSpringData-Description");
		application.setCreatedBy(getUser());
		application.setCreatedDateTime(getDate());
		application.setUpdatedBy(getUser());
		application.setUpdatedDateTime(getDate());
		return application;
	}

}
