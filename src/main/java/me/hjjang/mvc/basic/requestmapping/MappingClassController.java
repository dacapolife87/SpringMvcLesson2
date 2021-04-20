package me.hjjang.mvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;


@RestController
public class MappingClassController {

    /**
     * 회원 목록 조회: GET /users
     * 회원 등록: POST /users
     * 회원 조회: GET /users/{userId}
     * 회원 수정: PATCH /users/{userId}
     * 회원 삭제: DELETE /users/{userId}
     */

    @GetMapping("/mapping/users")
    public String user() {
        return "get Users";
    }

    @PostMapping("/mapping/users")
    public String addUser() {
        return "post User";
    }

    @GetMapping("/mapping/users/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get Users = " + userId;
    }

    @PatchMapping("/mapping/users/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update Users = " + userId;
    }

    @DeleteMapping("/mapping/users/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete Users = " + userId;
    }
}
