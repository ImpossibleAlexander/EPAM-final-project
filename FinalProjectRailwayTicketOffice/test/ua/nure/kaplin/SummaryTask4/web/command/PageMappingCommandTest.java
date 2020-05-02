package ua.nure.kaplin.SummaryTask4.web.command;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class PageMappingCommandTest  extends Mockito{
	
		private final PageMappingCommand command =  new PageMappingCommand();
		
		@Test
		public void getMappingMainPage() throws ServletException, IOException, AppException {
				
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			
			when(request.getParameter("page")).thenReturn("main_page");
			
			assertTrue(Path.PAGE_MAIN.equals(command.execute(request, response)));
		}
		
		@Test
		public void getMappingLoginPage() throws ServletException, IOException, AppException {
				
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			
			when(request.getParameter("page")).thenReturn("login");

			assertTrue(Path.PAGE_LOGIN.equals(command.execute(request, response)));
		}
		
		@Test
		public void getMappingRegistrationPage() throws ServletException, IOException, AppException {
				
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			
			when(request.getParameter("page")).thenReturn("registration");
			
			assertTrue(Path.PAGE_REGISTRATION.equals(command.execute(request, response)));
		}
		
		@Test
		public void getMappingAdminPage() throws ServletException, IOException, AppException {
				
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			
			when(request.getParameter("page")).thenReturn("admin");
			
			assertTrue(Path.PAGE_ADMIN_MENU.equals(command.execute(request, response)));
		}
		
		@Test
		public void getMappingBasketPage() throws ServletException, IOException, AppException {
				
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			
			when(request.getParameter("page")).thenReturn("basket");
			
			assertTrue(Path.PAGE_BASKET.equals(command.execute(request, response)));
		}
		
		@Test
		public void getMappingUserPage() throws ServletException, IOException, AppException {
				
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			
			when(request.getParameter("page")).thenReturn("user_page");
			
			assertTrue(Path.PAGE_USER_PAGE.equals(command.execute(request, response)));
		}
		
		@Test
		public void getMappingErrorPage() throws ServletException, IOException, AppException {
				
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			
			when(request.getParameter("page")).thenReturn("error_page");
			
			assertTrue(Path.PAGE_ERROR.equals(command.execute(request, response)));
		}
}
