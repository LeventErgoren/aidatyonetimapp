package com.example.controller;

import com.example.entity.Block;
import java.util.List;

public interface IRestBlockController {
    List<Block> getAllBlocks();
    Block createBlock(String name);
}
