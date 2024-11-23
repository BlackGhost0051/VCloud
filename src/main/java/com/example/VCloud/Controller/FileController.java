    package com.example.VCloud.Controller;

    import org.springframework.stereotype.Controller;

    @Controller
    public class FileController {

        /*private static final String UPLOAD_DIR = System.getProperty("user.dir") + "src/main/resources/cloud/";

        @Autowired
        JwtUtil jwtUtil;  // JWT

        @GetMapping("/files")  // GOOD
        @ResponseBody
        public ResponseEntity<?> listFiles(HttpServletRequest request) {  // JWT  add HttpServletRequest request
            List<String> filenames = new ArrayList<>();

            Cookie[] cookies = request.getCookies();  // JWT
            if (cookies != null) {
                for (Cookie cookie : cookies) { // JWT
                    if (cookie.getName().equals("jwt_token")) { // JWT
                        String jwtToken = cookie.getValue(); // JWT
                        boolean jwtStatus = jwtUtil.validateToken(jwtToken); // JWT

                        if (jwtStatus) { // JWT
                            File[] files = new File(UPLOAD_DIR).listFiles();
                            if (files != null) {
                                for (File file : files) {
                                    if (file.isFile()) {
                                        filenames.add(file.getName());
                                    }
                                }
                            }
                            return ResponseEntity.ok(filenames); // JWT
                        } else {
                            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token is invalid"); // JWT
                        }
                    }
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token is missing"); // JWT
        }


        @PostMapping("/upload")
        public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("jwt_token")) {
                        String jwtToken = cookie.getValue();
                        boolean jwtStatus = jwtUtil.validateToken(jwtToken);

                        if (jwtStatus) {
                            try {
                                byte[] bytes = file.getBytes();
                                Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
                                Files.write(path, bytes);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return "redirect:/send";
                        } else {
                            return "error";
                        }
                    }
                }
            }

            return "error";
        }

        @GetMapping("/delete")
        public String deleteFile(@RequestParam("filename") String filename, HttpServletRequest request) throws IOException {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("jwt_token")) {
                        String jwtToken = cookie.getValue();
                        boolean jwtStatus = jwtUtil.validateToken(jwtToken);

                        if (jwtStatus) {
                            String filePath = UPLOAD_DIR + filename;
                            File file = new File(filePath);

                            if (file.exists()) {
                                if (file.delete()) {
                                    // File deleted successfully, redirect to /send
                                    return "redirect:/send";
                                } else {
                                    return "error"; // Internal server error
                                }
                            } else {
                                return "error"; // File not found
                            }
                        } else {
                            return "error"; // Unauthorized
                        }
                    }
                }
            }
            return "error"; // Unauthorized
        }



        @GetMapping("/download")
        public ResponseEntity<?> downloadFile(@RequestParam("filename") String filename, HttpServletRequest request) throws IOException {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("jwt_token")) {
                        String jwtToken = cookie.getValue();
                        boolean jwtStatus = jwtUtil.validateToken(jwtToken);

                        if (jwtStatus) {
                            String filePath = UPLOAD_DIR + filename;
                            File file = ResourceUtils.getFile(filePath);
                            Resource resource = new FileSystemResource(file);

                            return ResponseEntity.ok()
                                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                                    .body(resource);
                        } else {
                            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token is invalid");
                        }
                    }
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token is missing");
        }



        @GetMapping("/send")
        public String sendPage(HttpServletRequest request) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("jwt_token")) {
                        String jwtToken = cookie.getValue();
                        boolean jwtStatus = jwtUtil.validateToken(jwtToken);

                        if (jwtStatus) {
                            return "cloud_send";
                        } else {
                            return "error";
                        }
                    }
                }
            }
            return "error";
        }*/
}