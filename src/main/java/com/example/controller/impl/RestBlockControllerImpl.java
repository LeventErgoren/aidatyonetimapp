package com.example.controller.impl;

import com.example.controller.IRestBlockController;
import com.example.entity.Block;
import com.example.service.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blocks")
public class RestBlockControllerImpl implements IRestBlockController {

    @Autowired
    IBlockService blockService;

    @GetMapping("/list")
    @Override
    public List<Block> getAllBlocks() {
        return blockService.getAllBlocks();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')") // Sadece Admin blok ekleyebilir
    @Override
    public Block createBlock(@RequestParam String name) {
        return blockService.createBlock(name);
    }
}
