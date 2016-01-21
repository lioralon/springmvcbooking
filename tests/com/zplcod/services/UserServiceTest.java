
import com.zplcod.services.UserService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Clancy on 2016/1/21.
 */

import com.zplcod.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Clancy on 2016/1/20.
 */
//basing on junit 4
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/applicationContext.xml",
        "classpath*:/applicationContext-test.xml",})
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Test
    public void testHasMatchUser() throws Exception {

        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "111111");
        assert (b1);
        assert (!b2);
    }

    @Test
    public void testFindUserbyUserName() throws Exception {
        User user = userService.findUserbyUserName("admin");
        assertEquals(user.getUserName(), "admin");
    }

    @Test
    public void testLoginSuccess() throws Exception {
        User user = userService.findUserbyUserName("admin");
        int credit = user.getCredits();
        userService.loginSuccess(user);
//        assertEquals();
    }


}