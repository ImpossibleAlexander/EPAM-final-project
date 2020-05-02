package ua.nure.kaplin.SummaryTask4;


public final class Path {
	
	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR = "/error_page.jsp";
	public static final String PAGE_ADMIN_MENU = "/WEB-INF/view/admin/admin_edit_route_point.jsp";
	public static final String PAGE_ADMIN_MAIN_PAGE = "/WEB-INF/view/admin/admin_main_page.jsp";
	public static final String PAGE_REGISTRATION= "/registration.jsp";
	public static final String PAGE_ROUTE_DETAILS= "/routeDetails.jsp";
	public static final String PAGE_MAIN= "/main_page.jsp";
	public static final String PAGE_BASKET= "/WEB-INF/view/basket.jsp";
	public static final String PAGE_USER_PAGE= "/WEB-INF/view/user_page.jsp";
	
	// path for redirect
	public static final String PAGE_MAIN_REDIRECT = "controller?command=mapping&page=main_page";
	public static final String PAGE_MAIN_FOR_ADMIN_REDIRECT = "controller?command=mapping&page=admin_main_page";
	public static final String PAGE_ERROR_REDIRECT = "controller?command=mapping&page=error_page";
	public static final String PAGE_ADMIN_MENU_REDIRECT = "controller?command=mapping&page=admin";
	public static final String PAGE_LOGIN_REDIRECT = "controller?command=mapping&page=login";
	public static final String PAGE_BASKET_REDIRECT = "controller?command=mapping&page=basket";


}