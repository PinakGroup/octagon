package controller;

import com.kids.crm.OctagonApplication;
import com.kids.crm.config.Config;
import com.kids.crm.config.WebSecurityConfig;
import com.kids.crm.controller.RegistrationController;
import com.kids.crm.repository.GuardianRepository;
import com.kids.crm.repository.StudentBatchInterestRepository;
import com.kids.crm.repository.StudentRefereeRepository;
import com.kids.crm.repository.SubjectRepository;
import com.kids.crm.service.BatchService;
import com.kids.crm.validator.SignupValidator;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RegistrationController.class)
@ContextConfiguration(classes = OctagonApplication.class)
@Import({WebSecurityConfig.class, Config.class})
public class RegistrationControllerTest extends BaseController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private SignupValidator validator;
    @MockBean private BatchService batchService;
    @Autowired private PasswordEncoder passwordEncoder;
    @MockBean private GuardianRepository guardianRepository;
    @MockBean private StudentBatchInterestRepository studentBatchInterestRepository;
    @MockBean private SubjectRepository subjectRepository;
    @MockBean private StudentRefereeRepository studentRefereeRepository;

    @Test
    public void registerGetPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("Register an Account")));

    }
}
