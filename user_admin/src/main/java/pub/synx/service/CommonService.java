package pub.synx.service;

import pub.synx.pojo.po.User;

/**
 * @author SynX TA
 * @version 2024
 **/
public interface CommonService {

    boolean login(String account, String password);

    String register(User user);
}
