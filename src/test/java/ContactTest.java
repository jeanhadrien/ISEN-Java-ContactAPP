import org.junit.jupiter.api.Test;
import static junit.framework.Assert.*;

public class ContactTest {
    @Test
    void canCreateContact(){
        Contact cont = new Contact();

        assertNotNull(cont);
    }

    @Test
    void canSetAttributes(){
        Contact cont = new Contact();

        cont.setBirth("1990","10","10");
        cont.setAddress("45 rue Truc, 50944, Ville");
        cont.setEmail("testemail@test.test");
        cont.setPhone("0123456789");
        cont.setName("Firstname","Lastname");
        cont.setId(3);
    }


}
