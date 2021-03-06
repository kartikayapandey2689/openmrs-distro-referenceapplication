package org.openmrs.reference;

/**
 * Created by nata on 25.06.15.
 */
import org.junit.*;
import static org.junit.Assert.*;

import org.openmrs.reference.page.HeaderPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.uitestframework.test.TestBase;


public class TransferToWardServiceTest extends TestBase {
    private HomePage homePage;
    private ClinicianFacingPatientDashboardPage patientDashboardPage;
    private HeaderPage headerPage;



    @Before
    public void setUp() throws Exception {

        
        homePage = new HomePage(page);
        assertPage(homePage);
        patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
        headerPage = new HeaderPage(driver);
        homePage.goToActiveVisitPatient();


    }
    @Test
    public void transferToWardServiceTest() throws Exception {

        if(patientDashboardPage.inpatientPresent()) {
            patientDashboardPage.exitFromInpatient();

        }
        patientDashboardPage.clickOnAdmitToInpatient();
        patientDashboardPage.selectLocation("Unknown Location");
        patientDashboardPage.clickOnSave();
        assertTrue(patientDashboardPage.visitLink().getText().contains("Entered Admission"));
        patientDashboardPage.waitForVisitLinkHidden();
        patientDashboardPage.clickOnTranfer();
        patientDashboardPage.selectLocation("Isolation Ward");
        assertTrue(patientDashboardPage.location().getText().contains("Isolation Ward"));
        patientDashboardPage.clickOnSave();
        assertTrue(patientDashboardPage.visitLink().getText().contains("Entered Transfer Within Hospital"));
        patientDashboardPage.exitFromInpatient();
    }


    @After
    public void tearDown() throws Exception {
        headerPage.clickOnHomeIcon();
        headerPage.logOut();
    }

}
