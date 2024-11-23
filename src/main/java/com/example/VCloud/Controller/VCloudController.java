package com.example.VCloud.Controller;

import org.springframework.stereotype.Controller;

@Controller
public class VCloudController {
    /*@Autowired
    private Users users;
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hi";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return users.getAllUsers();
    }




        @Autowired
        private JwtUtil jwtUtil;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody MultiValueMap<String, String> loginData, HttpServletResponse response) {
        String login = loginData.getFirst("login");
        String password = loginData.getFirst("password");

        List<User> userList = users.getAllUsers();

        for (User user : userList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                System.out.println("GOOD");
                String token = jwtUtil.generateToken(login);

                Cookie cookie = new Cookie("jwt_token", token);
                cookie.setPath("/");
                cookie.setSecure(true);
                cookie.setHttpOnly(true);

                response.addCookie(cookie);

                return ResponseEntity.ok().body(token);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
    }
*/
}
