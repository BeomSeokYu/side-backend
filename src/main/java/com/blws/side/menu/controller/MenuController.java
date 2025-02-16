package com.blws.side.menu.controller;

import com.blws.side.menu.dto.RequestMenuCreateDTO;
import com.blws.side.menu.dto.RequestMenuUpdateDTO;
import com.blws.side.menu.dto.ResponseMenuDTO;
import com.blws.side.menu.dto.ResponseRouteMenuDTO;
import com.blws.side.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuController {

    private final MenuService MenuService;

    @GetMapping("/menu")
    public List<ResponseMenuDTO> getMenuAll() {
        return MenuService.getMenuAll().stream()
                .map(ResponseMenuDTO::of)
                .toList();
    }

    @GetMapping("/menu/route")
    public List<ResponseRouteMenuDTO> getRouteMenuList() {
        return MenuService.getRouteMenuList().stream()
                .map(ResponseRouteMenuDTO::of)
                .toList();
    }

    @GetMapping("/menu/list")
    public List<ResponseMenuDTO> getMenuList() {
        return MenuService.getMenuList().stream()
                .map(ResponseMenuDTO::of)
                .toList();
    }

    @GetMapping("/menu/{id}")
    public ResponseMenuDTO getMenu(@PathVariable("id") Long id) {
        return ResponseMenuDTO.of(MenuService.getMenuById(id));
    }

    // ResponseDTO 사용
//    @GetMapping("/Menu/{id}")
//    public ResponseDTO getMenu(@PathVariable("id") Long id) {
//        return ResponseDTO.of(MenuService.getMenuById(id));
//    }

    @PostMapping("/menu")
    public ResponseMenuDTO createMenu(@RequestBody RequestMenuCreateDTO requestMenuCreateDTO) {
        return ResponseMenuDTO.of(MenuService.createMenu(requestMenuCreateDTO.toEntity()));
    }

    @PutMapping("/menu/{id}")
    public ResponseMenuDTO updateMenu(@PathVariable("id") Long id,
                                          @RequestBody RequestMenuUpdateDTO requestMenuUpdateDTO) {
        return ResponseMenuDTO.of(MenuService.updateMenu(id, requestMenuUpdateDTO.toEntity()));
    }

    @DeleteMapping("/menu/{id}")
    public ResponseMenuDTO deleteMenu(@PathVariable("id")Long id) {
        return ResponseMenuDTO.of(MenuService.deleteMenu(id));
    }

}
