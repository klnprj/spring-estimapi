package com.estima.app;

import com.estima.ServiceLauncher;
import com.estima.domain.Building;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceLauncher.class)
@ActiveProfiles("test")
@Transactional
@SqlGroup({
        @Sql("/testdata/users-records.sql"),
        @Sql("/testdata/authorities-records.sql"),
        @Sql("/testdata/clients-records.sql"),
        @Sql("/testdata/dictionary-records.sql"),
        @Sql("/testdata/dictionary-item-records.sql"),
        @Sql("/testdata/building-records.sql")
})
public class BuildingSelectionTests {

    @Autowired
    private ManageBuilding manageBuilding;

    @Test
    public void givenBuildingExists_whenGettingById_thenReturnedComplete() throws Exception {
        Long buildingId = 1L;
        Building building = manageBuilding.get(buildingId);

        assertThat(building, allOf(
                hasProperty("id", is(1L)),
                hasProperty("name", is("Building 1")),
                hasProperty("address", is("Address_1")),
                hasProperty("location", is("POINT(40.0 40.0)")),
                hasProperty("client", hasProperty("id", is(10L)))
        ));
    }
}
