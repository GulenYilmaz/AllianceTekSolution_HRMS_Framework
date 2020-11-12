package com.ats.testbase;

import com.ats.pages.ATSCourseManagementPageElements;
import com.ats.pages.ATSDashboardPageElements;
import com.ats.pages.ATSLoginPageElements;
import com.ats.pages.ATSTeacherManagementPageElements;
import com.ats.pages.ATSUserManagementPageElements;

public class PageInitializer extends BaseClass {
	
public static ATSLoginPageElements ATSlogin;
public static ATSCourseManagementPageElements cmp;
public static ATSTeacherManagementPageElements ATStmp;
public static ATSDashboardPageElements ATSdb;	
public static ATSUserManagementPageElements ATSump;

public static void initialize() {
	
	cmp= new ATSCourseManagementPageElements();
	ATSlogin= new ATSLoginPageElements();
	ATStmp= new ATSTeacherManagementPageElements();
	ATSdb= new ATSDashboardPageElements();
	ATSump = new ATSUserManagementPageElements();

}
}
