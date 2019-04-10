package next.controller;

import java.util.HashMap;
import java.util.Map;

public class ControllerMapping {
    private static Map<String,Controller> controllerMap = new HashMap<>();

    public static void initController() {
        controllerMap.put("/users/create", new CreateUserController());
        controllerMap.put("/users/form", new CreateUserController());
        controllerMap.put("", new HomeController());
        controllerMap.put("/users", new ListUserController());
        controllerMap.put("/users", new ListUserController());
        controllerMap.put("/users/login", new LoginController());
        controllerMap.put("/users/loginForm", new LoginController());
        controllerMap.put("/users/logout", new LogoutController());
        controllerMap.put("/users/profile", new ProfileController());
        controllerMap.put("/users/update", new UpdateUserController());
        controllerMap.put("/users/updateForm", new UpdateUserController());
    }

    public Controller get(String request) {
        if(controllerMap.get(request) == null) {
            throw  new IllegalArgumentException();
        }
        return controllerMap.get(request);
    }
}
