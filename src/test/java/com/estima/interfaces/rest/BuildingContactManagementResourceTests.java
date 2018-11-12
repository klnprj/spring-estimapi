package com.estima.interfaces.rest;

import com.estima.ServiceLauncher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceLauncher.class)
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
public class BuildingContactManagementResourceTests {

    @Test
    public void givenContactsExist_whenGettingList_thenReturned() throws Exception {
        //todo:
    }

    @Test
    public void givenContactNotExists_whenPosting_thenCreated() throws Exception {
        //todo:
    }

    @Test
    public void givenContactExists_whenDeleting_thenRemoved() throws Exception {
        //todo:
    }
}
